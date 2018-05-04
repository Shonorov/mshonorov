package ru.job4j.tracker;
import org.hamcrest.core.IsNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class StartUITest {

    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final String menu = new StringJoiner(System.lineSeparator(), "", "")
            .add("Menu:")
            .add("0 : Add new Item")
            .add("1 : Show all items")
            .add("2 : Edit item")
            .add("3 : Delete item")
            .add("4 : Find item by Id")
            .add("5 : Find items by name").toString();
    /**
     * Stores output from the console to ByteArray.
     * @Before runs method before test.
     */
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }
    /**
     * Returns output from ByteArray back to the console.
     * @After runs method after test.
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker("config.properties");
        tracker.cleanTable();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "y"});
        new StartUI(input, tracker).init();
        String result = new StringJoiner(System.lineSeparator(), "", "")
                .add(menu)
                .add(String.format("%stest name%s%n", "------------Новая заявка с именем : '", "' создана-----------")).toString();
        assertThat(new String(this.out.toByteArray()), is(result));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker("config.properties");
        tracker.cleanTable();
        Item item = tracker.add(new Item("111", "111"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByID(item.getId()).getName(), is("test name"));
    }

    @Test
    public void whenDeleteThenEmptyItemsArray() {
        Tracker tracker = new Tracker("config.properties");
        tracker.cleanTable();
        Item item = tracker.add(new Item("111", "111"));
        Input input = new StubInput(new String[]{"3", item.getId(), "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().size(), is(0));
    }

    @Test
    public void whenFindByIDThenFoundTheSameItem() {
        Tracker tracker = new Tracker("config.properties");
        tracker.cleanTable();
        Item item = tracker.add(new Item("111", "111"));
        Input input = new StubInput(new String[]{"4", item.getId(), "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0).getId(), is(item.getId()));
    }

    @Test
    public void whenShowAllItems() {
        Tracker tracker = new Tracker("config.properties");
        tracker.cleanTable();
        Item item1 = tracker.add(new Item("Name1", "Desc1", 1));
        Item item2 = tracker.add(new Item("Name2", "Desc2", 2));
        Input input = new StubInput(new String[]{"1", "y"});
        String result = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(menu)
                .add("------------Вывод всех заявок--------------")
                .add("id\t\t\t\t\tname\tdesc\tcreated\r")
                .add(item1.getId() + "\tName1\tDesc1\t1")
                .add(item2.getId() + "\tName2\tDesc2\t2")
                .add("------------Список заявок выведен----------").toString();
        new StartUI(input, tracker).init();
        assertThat(new String(this.out.toByteArray()), is(result));
    }

    @Test
    public void whenFindByName() {
        Tracker tracker = new Tracker("config.properties");
        tracker.cleanTable();
        Item item1 = tracker.add(new Item("Name1", "Desc1", 1));
        Item item2 = tracker.add(new Item("Name2", "Desc2", 2));
        Item item3 = tracker.add(new Item("fail1", "Desc3", 3));
        Input input = new StubInput(new String[]{"5", "Name", "y"});
        String result = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(menu)
                .add("-------Поиск заявки по имени------")
                .add("id\t\t\t\t\tname\tdesc\tcreated\r")
                .add(item1.getId() + "\tName1\tDesc1\t1")
                .add(item2.getId() + "\tName2\tDesc2\t2")
                .add("-------Заявки найдены-------------").toString();
        new StartUI(input, tracker).init();
        assertThat(new String(this.out.toByteArray()), is(result));
    }
}