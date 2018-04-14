package ru.job4j.generic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Array realization with generics.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleArray<T> implements Iterable {
    /**
     * Arrau of generic objects.
     * Current position.
     */
    private T[] objects;
    private int position = 0;

    public SimpleArray(T[] objects) {
        this.objects = objects;
    }
    /**
     * Get element by index.
     * @param index index.
     * @return array value.
     */
    public T get(int index) {
        return objects[index];
    }
    /**
     * Add value to the end of array.
     * @param model value.
     * @return if success.
     */
    public boolean add(T model) {
        objects[position++] = model;
        return true;
    }
    /**
     * Insert value to given position.
     * @param index index of position.
     * @param model value to insert.
     */
    public void set(int index, T model) {
        if (objects.length <= position) {
            objects = Arrays.copyOf(objects, position * 2);
        }
        for (int i = position + 1; i > index; i--) {
            objects[i] = objects[i - 1];
        }
        objects[index] = model;
    }
    /**
     * Delete value by index.
     * @param index position.
     */
    public void delete(int index) {
        for (int i = index; i < position; i++) {
            objects[i] = objects[i + 1];
        }
        objects[position] = null;
    }

    /**
     * Get model index.
     * @param model to find.
     * @return model index.
     */
    public int indexOf(T model) {
        int result = -1;
        for (int i = 0; i < position; i++) {
            if (objects[i].equals(model)) {
                result = i;
            }
        }
        return result;
    }

    /**
     * Custom iterator.
     * @return Iterator.
     */
    @Override
    public Iterator iterator() {
        return new Iterator() {

            private T[] values = objects;
            private int iterPosition = 0;

            @Override
            public boolean hasNext() {
                return values[iterPosition] != null;
            }

            @Override
            public Object next() {
                if (values[iterPosition] == null) {
                    throw new NoSuchElementException();
                }
                return values[iterPosition++];
            }
        };
    }
}
