package ru.job4j.tracker;

import java.sql.Connection;
import java.time.LocalTime;
import java.util.ArrayList;
/**
 * Class for items store and management.
 * @author MShonorov (shonorov@gmail.com).
 * @version $Id$.
 * @since 0.1.
 */
public class Tracker implements AutoCloseable {
    /**
     * Массив для хранения заявок.
     * Database connection.
     */
    private final ArrayList<Item> items = new ArrayList<>();
    Connection connection = null;

    @Override
    public void close() throws Exception {
        //TODO
        if(connection != null) {
            connection.close();
        }
    }

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     * @return added item.
     */
    public Item add(Item item) {
        item.setId(generateId());
        items.add(item);
        return item;
    }
    /**
     * Replace one item bt another by ID.
     * @param id to replace.
     * @param item replace by.
     */
    public void replace(String id, Item item) {
        for (Item entry : items) {
            if (entry.getId() == id) {
                items.set(items.indexOf(entry), item);
                return;
            }
        }
    }
    /**
     * Removes item from array by id.
     * @param id to delete.
     */
    public void delete(String id) {
        for (Item entry : items) {
            if (entry.getId().equals(id)) {
                items.remove(items.indexOf(entry));
                return;
            }
        }
    }
    /**
     * Returns all current items.
     * @return items array.
     */
    public ArrayList<Item> findAll() {
        return items;
    }
    /**
     * Finds items by name.
     * @param key searchname.
     * @return result array.
     */
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item entry : items) {
            if (entry.getName().contains(key)) {
                result.add(entry);
            }
        }
        return result;
    }
    /**
     * Finds item by id string.
     * @param id string.
     * @return item if found, null if not found.
     */
    public Item findByID(String id) {
        Item result = null;
        for (Item entry : items) {
            if (entry.getId().equals(id)) {
                result = entry;
            }
        }
        return result;
    }
    /**
     * Метод генерирует уникальный ключ для заявки на основании времени и произвольного числа.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return LocalTime.now().toString() + (short) (Math.random() * 10000);
    }
}