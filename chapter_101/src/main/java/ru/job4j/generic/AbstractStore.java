package ru.job4j.generic;

import java.util.Iterator;
/**
 * Abstract store for generic types.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public abstract class AbstractStore<T extends Base> implements Store {

    /**
     * SimpleArray of generics.
     */
    private SimpleArray<T> content;

    public AbstractStore(SimpleArray<T> content) {
        this.content = content;
    }
    /**
     * Add object to SimpleArray.
     * @param model object to add.
     */
    @Override
    public void add(Base model) {
        content.add((T) model);
    }
    /**
     * Replaces one object with another by id.
     * @param id object to replace.
     * @param model replaced by.
     * @return true if success.
     */
    @Override
    public boolean replace(String id, Base model) {
        boolean result = false;
        int index = findIndex(id);
        if (index != -1) {
            content.set(index, (T) model);
            result = true;
        }
        return result;
    }
    /**
     * Deletes object from array by id.
     * @param id id to delete.
     * @return true if success.
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = findIndex(id);
        if (index != -1) {
            content.delete(index);
            result = true;
        }
        return result;
    }
    /**
     * Finds object by id.
     * @param id id to find.
     * @return object or null.
     */
    @Override
    public Base findById(String id) {
        T result = null;
        Iterator<T> iter = content.iterator();
        while (iter.hasNext()) {
            T current = iter.next();
            if (current.getId().equals(id)) {
                result = current;
                break;
            }
        }
        return result;
    }

    /**
     * Finds array index of object by id.
     * @param id id to find.
     * @return index or -1 if not found.
     */
    private int findIndex(String id) {
        int result = -1;
        Iterator<T> iter = content.iterator();
        while (iter.hasNext()) {
            T current = iter.next();
            if (current.getId().equals(id)) {
                result = content.indexOf(current);
            }
        }
        return result;
    }
}
