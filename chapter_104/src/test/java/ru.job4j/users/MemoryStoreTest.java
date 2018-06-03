package ru.job4j.users;
import org.junit.Test;

import java.time.LocalDateTime;

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
        User user1 = new User("test1", "user1", "user1", "user1@contoso.com", LocalDateTime.now(), "user1", "user", "0", "0");
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

    @Test
    public void whenStartThenLiquibasePrepareDatabase() {
        MemoryStore store = new MemoryStore("users.properties");
        Role admin = new Role("administrator", true);
        Role user = new Role("user", false);
        assertTrue(store.getRoles().size() >= 2);
        assertTrue(store.getRoles().contains(admin));
        assertTrue(store.getRoles().contains(user));
        User administrator = new User("0", "admin", "admin", "admin@contoso.com", LocalDateTime.now(), "admin", "administrator", "0", "0");
        User guest = new User("1", "guest", "guest", "guest@contoso.com", LocalDateTime.now(), "guest", "user", "0", "0");
        assertTrue(store.findAll().size() >= 2);
        assertTrue(store.findAll().contains(administrator));
        assertTrue(store.findAll().contains(guest));
    }

    @Test
    public void whenRussiaThenFindCities() {
        MemoryStore store = new MemoryStore("users.properties");
        assertNotEquals(store.getCitiesByCountryName("0").size(), 0);
    }
}
