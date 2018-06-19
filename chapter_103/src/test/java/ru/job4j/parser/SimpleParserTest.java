package ru.job4j.parser;

import org.junit.Test;

import static java.util.concurrent.TimeUnit.*;

public class SimpleParserTest {

    @Test
    public void whenScheduleThenStop() throws InterruptedException {
        SimpleParser parser = new SimpleParser("parser.properties");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                parser.schedule(2, 3, SECONDS);
            }
        });
        thread.start();
        Thread.sleep(4000);
    }

    @Test
    public void whenParseThenParse() {
        SimpleParser parser = new SimpleParser("parser.properties");
        parser.parse();
    }
}
