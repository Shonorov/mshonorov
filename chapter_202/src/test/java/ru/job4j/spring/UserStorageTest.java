package ru.job4j.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static org.junit.Assert.*;
/**
 * UserStorage test.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UserStorageTest {

    @Test
    public void whenAddUserThenItSaved() {
        MemoryStorage memory = new MemoryStorage();
        UserStorage storage = new UserStorage(memory);
        storage.add(new User("User"));
        assertNotNull(storage);
    }

    @Test
    public void whenLoadContextThenGetBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        MemoryStorage memory = context.getBean(MemoryStorage.class);
        memory.add(new User("User1"));
        assertNotNull(memory);
        JdbcStorage database = context.getBean(JdbcStorage.class);
        database.add(new User("User2"));
        assertNotNull(database);
        UserStorage storage = context.getBean(UserStorage.class);
        assertNotNull(storage);
    }
}