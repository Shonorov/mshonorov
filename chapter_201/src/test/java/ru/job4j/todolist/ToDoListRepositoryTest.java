package ru.job4j.todolist;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ToDoListRepositoryTest {

    @Test
    public void whenCreateThenFind() {
        ToDoListRepository repository = new ToDoListRepository();
        String description = "Test1";
        repository.createItem(description);
        List<Item> items = repository.getAllItems();
        Item expect = new Item();
        for (Item item : items) {
            if (item.getDescription().equals(description)) {
                expect = item;
            }
        }
        assertThat(expect.getDescription(), is(description));
    }

    @Test
    public void whenGetOnlyCurrentThenGetOneLess() {
        ToDoListRepository repository = new ToDoListRepository();
        String description = "Test2";
        repository.createItem(description);
        int size = repository.getOnlyCurrentItems().size();
        Item expect = new Item();
        for (Item item : repository.getAllItems()) {
            if (item.getDescription().equals(description)) {
                expect = item;
            }
        }
        repository.setDone(String.valueOf(expect.getId()), true);
        assertThat(repository.getOnlyCurrentItems().size(), is(size - 1));
    }

    @Test
    public void whenSetDoneThenDoneIsChanged() {
        ToDoListRepository repository = new ToDoListRepository();
        String description = "Test3";
        repository.createItem(description);
        Item expect = new Item();
        for (Item item : repository.getAllItems()) {
            if (item.getDescription().equals(description)) {
                expect = item;
            }
        }
        repository.setDone(String.valueOf(expect.getId()), true);
        for (Item item : repository.getAllItems()) {
            if (item.getDescription().equals(description)) {
                expect = item;
            }
        }
        assertThat(expect.isDone(), is(true));
    }

    @Test
    public void whenCreateThenFindById() {
        ToDoListRepository repository = new ToDoListRepository();
        String description = "Test4";
        repository.createItem(description);
        Item expect = new Item();
        for (Item item : repository.getAllItems()) {
            if (item.getDescription().equals(description)) {
                expect = item;
            }
        }
        assertThat(repository.findByID(String.valueOf(expect.getId())).get().getId(), is(expect.getId()));
    }

}