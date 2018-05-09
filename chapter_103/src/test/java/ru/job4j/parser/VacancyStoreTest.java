package ru.job4j.parser;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
public class VacancyStoreTest {

    @Test
    public void whenDBCreateThenInsertNoDuplicates() throws SQLException {
        VacancyStore store = new VacancyStore("parser.properties");
        store.cleanTable();
        ArrayList<Vacancy> vacancies = new ArrayList<>();
        vacancies.add(new Vacancy("vacancy1", "http://vacancy1.html"));
        store.addAll(vacancies);
        assertThat(vacancies, is(store.findAll()));
        vacancies.add(new Vacancy("vacancy1", "http://vacancy1.html"));
        store.addAll(vacancies);
        assertNotEquals(vacancies, store.findAll());
    }
}
