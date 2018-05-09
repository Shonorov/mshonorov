package ru.job4j.parser;
/**
 * Vacancy.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Vacancy {

    private String title;
    private String url;

    public Vacancy(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return this.title + " " + this.url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vacancy)) {
            return false;
        }

        Vacancy vacancy = (Vacancy) o;

        if (!title.equals(vacancy.title)) {
            return false;
        }
        return url.equals(vacancy.url);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
}
