package ru.job4j.users;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Persistent layout for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MemoryStore implements Store {

    private static final MemoryStore instance = new MemoryStore();

    public static MemoryStore getInstance() {
        return instance;
    }

    //TODO Сделать реализацию этого интерфейса MemoryStore. Сделать из него синлетон.
    //Класс MemoryStore - должен быть потокобезопастный. вы можете использовать внутри потокбезопастные коллекции.
    //В web.xml указать для UserServiler режим load-on-startup

    @Override
    public void add(User user) {

    }

    @Override
    public void update(User user, User update) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public List<User> findAll() {
        return new CopyOnWriteArrayList<User>();
    }

    @Override
    public User findById(String id) {
        return new User("", "", "");
    }
}
