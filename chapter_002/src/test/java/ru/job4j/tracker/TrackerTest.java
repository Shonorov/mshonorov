package ru.job4j.tracker;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class TrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    @Test
    public void whenAddItemThenGetNotNullId() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item());
        String expect = item.getId();
        assertThat(expect, is(IsNull.notNullValue()));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findByID(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteThenNoItem() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("test1", "testDescription", 123L));
        String id = tracker.findAll()[0].getId();
        tracker.delete(id);
        int result = tracker.findAll().length;
        assertThat(result, is(0));
    }

    @Test
    public void whenWhenFindAllSizeOfTwo() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        tracker.add(next);
        assertThat(tracker.findAll().length, is(2));
    }

    @Test
    public void whenFindByIdThenRightItem() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        String findId = previous.getId();
        Item next = new Item("test2", "testDescription2", 1234L);
        tracker.add(next);
        Item result = tracker.findByID(findId);
        assertThat(result, is(previous));
    }

    @Test
    public void whenFindByNameThenRightItem() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("test1", "testDescription", 123L));
        tracker.add(new Item("test2", "testDescription2", 456L));
        tracker.add(new Item("fail", "failDescription", 789L));
        Item[] result = tracker.findByName("test");
        assertThat(result.length, is(2));
    }

}