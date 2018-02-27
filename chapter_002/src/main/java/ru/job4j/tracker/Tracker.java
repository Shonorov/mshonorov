package ru.job4j.tracker;

import java.time.LocalTime;
import java.util.Arrays;
/**
 * Class for items store & management.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];
    /**
     * Next free position.
     */
    private int position = 0;
    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }
    /**
     * Replace one item bt another by ID.
     * @param id to replace.
     * @param item replace by.
     */
    public void replace(String id, Item item) {
        Item replace = findByID(id);
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(replace)) {
                items[i] = item;
                break;
            }
        }
    }
    /**
     * Removes item from array by id.
     * @param id to delete.
     */
    public void delete(String id) {
        Item delete = findByID(id);
        if ((delete == null) || (position == 0)) {
            return;
        }
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(delete)) {
                items[i] = items[position - 1];
                items[position - 1] = null;
                position--;
                break;
            }
        }
    }
    /**
     * Returns all current items.
     * @return items array.
     */
    public Item[] findAll() {
        return Arrays.copyOf(this.items, position);
    }
    /**
     * Finds items by name.
     * @param key searchname.
     * @return result array.
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[this.items.length];
        int innerPosition = 0;
        for (int i = 0; i < position; i++) {
            if (items[i].getName().contains(key)) {
                result[innerPosition] = items[i];
                innerPosition++;
            }
        }
        return Arrays.copyOf(result, innerPosition);
    }
    /**
     * Finds item by id string.
     * @param id string.
     * @return item if found, null if not found.
     */
    public Item findByID(String id) {
        Item result = null;
        if (position != 0) {
            for (int i = 0; i < position; i++) {
                if (items[i].getId().equals(id)) {
                    result = items[i];
                    break;
                }
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