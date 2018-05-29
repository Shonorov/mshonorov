package ru.job4j.users;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MemoryStoreTest {
    @Test
    public void whenAddThenFind() {
        MemoryStore store = new MemoryStore("users.properties");
        User user1 = new User("user1", "user1", "user1@contoso.com");
        store.add(user1);
        assertTrue(store.findAll().contains(user1));
        store.delete(user1);
    }

    @Test
    public void whenModifiedThenUserChanged() {
        MemoryStore store = new MemoryStore("users.properties");
        User user2 = new User("user2", "user2", "user2@contoso.com");
        User user3 = new User("user3", "user3", "user3@contoso.com");
        store.add(user2);
        store.update(user2, user3);
        assertFalse(store.findAll().contains(user2));
        assertTrue(store.findAll().contains(user3));
        store.delete(user2);
    }

    @Test
    public void whenFindByIDThenDelete() {
        MemoryStore store = new MemoryStore("users.properties");
        User user4 = new User("user4", "user4", "user4@contoso.com");
        String id = user4.getId();
        store.add(user4);
        assertThat(store.findById(id).get(), is(user4));
        store.delete(user4);
        assertFalse(store.findAll().contains(user4));
    }

    @Test
    public void whenFindByNameThenDelete() {
        MemoryStore store = new MemoryStore("users.properties");
        User user5 = new User("user5", "user5", "user5@contoso.com");
        store.add(user5);
        assertThat(store.findByLogin("user5").get(), is(user5));
        store.delete(user5);
        assertFalse(store.findAll().contains(user5));
    }
}
