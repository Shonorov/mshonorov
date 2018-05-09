package ru.job4j.parser;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
/**
 * Simple job HTML parser.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleParser {

    /**
     * Parse start page.
     * Database management class unit.
     * Scheduler for parse job startup.
     * Application logger.
     */
    private static final String JOB = "http://www.sql.ru/forum/job";
    private VacancyStore store;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final static Logger LOGGER = Logger.getLogger(VacancyStore.class);

    public SimpleParser(String properties) {
        store = new VacancyStore(properties);
    }

    /**
     * Schedule parse runtime.
     * @param period repeat period.
     * @param delay end schedule delay.
     * @param timeUnit time unit of period and delay.
     */
    public void schedule(int period, int delay, TimeUnit timeUnit) {

        final Runnable parser = new Runnable() {
            @Override
            public void run() {
                LOGGER.info(Thread.currentThread().getName() + " started.");
                parse();
            }
        };

        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(parser, 0, period, timeUnit);
        scheduler.schedule(new Runnable() {
            public void run() {
                beeperHandle.cancel(true);
                LOGGER.info(Thread.currentThread().getName() + " stopped.");
            }
        }, delay, timeUnit);
    }

    /**
     * Parse HTML for Java vacancies.
     */
    public void parse() {
        ArrayList<Vacancy> vacancies = new ArrayList<>();
        int i = 1;
        while (true) {
            String url = JOB + "/" + i;
            try {
                Document doc = Jsoup.connect(url).get();
                Elements created = doc.getElementsByClass("altCol");
                if (!checkDate(created)) {
                    LOGGER.info("No current year vacancies on page: " + i);
                    break;
                }
                Elements elementsRefs = doc.getElementsByClass("postslisttopic");
                for (Element element : elementsRefs) {
                    String title = element.child(0).text().toLowerCase();
                    if (title.contains("java") && !title.contains("script")) {
                        vacancies.add(new Vacancy(title, element.child(0).attr("href")));
                    }
                }
            } catch (IOException e) {
                LOGGER.error("URL read failed.", e);
                e.printStackTrace();
                break;
            }
            i++;
        }
        try {
            store.addAll(vacancies);
        } catch (SQLException e) {
            LOGGER.error("Failed to write vacancies.", e);
            e.printStackTrace();
        }
    }

    /**
     * Check if page contains vacancies for current year.
     * @param elements vacancies date elements.
     * @return true if contains current year.
     */
    private boolean checkDate(Elements elements) {
        boolean result = false;
        String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        SimpleDateFormat formatter = new SimpleDateFormat("DD MMM YY, HH:mm");
        for (Element element : elements) {
            try {
                String current = formatter.parse(element.text()).toString();
                if (current.contains(year)) {
                    result = true;
                    break;
                }
            } catch (ParseException e) {
                //Do nothing.
            }
        }
        return result;
    }
}
