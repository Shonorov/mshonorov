package ru.job4j.map;
import java.util.*;
/**
 * Custom hash set.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleHashMap<K, V> implements Iterable<MapEntry<K, V>> {
    /**
     * Array of entries.
     * HashMap load factor.
     * Elements count.
     * Modifications count.
     */
    private MapEntry<K, V>[] map;
    private static final double LOAD_FACTOR = 0.75;
    private int size = 0;
    private int modCount = 0;

    public SimpleHashMap(MapEntry<K, V>[] map) {
        this.map = map;
    }

    public int getSize() {
        return size;
    }

    /**
     * Insert only unique by key elements to the map.
     * @param key of element.
     * @param value of element.
     * @return true if success.
     */
    public boolean insert(K key, V value) {
        if (size / map.length >= LOAD_FACTOR) {
            map = Arrays.copyOf(map, map.length * 2);
        }
        boolean result = false;
        int index = indexFor(hash(key.hashCode()), map.length);
        if (map[index] == null) {
            map[index] = new MapEntry<>(key, value);
            result = true;
            size++;
            modCount++;
        }
        return result;
    }

    /**
     * Return value by key. Throw NoSuchElementException if not found.
     * @param key to find.
     * @return value.
     */
    public V get(K key) {
        Optional<V> result = Optional.empty();
        int index = indexFor(hash(key.hashCode()), map.length);
        if (map[index] != null) {
            result = Optional.of(map[index].getValue());
        }
        if (!result.isPresent()) {
            throw new NoSuchElementException();
        }
        return result.get();
    }

    /**
     * Delete element by key.
     * @param key to delete.
     * @return false if element is null.
     */
    public boolean delete(K key) {
        boolean result = false;
        int index = indexFor(hash(key.hashCode()), map.length);
        if (map[index] != null) {
            map[index] = null;
            result = true;
            modCount++;
            size--;
        }
        return result;
    }
    /**
     * Generates hash from hashcode.
     * @param h key's hashcode.
     * @return hash.
     */
    private int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
    /**
     * Get array index for element in array with given length.
     * @param h hash(int h) result.
     * @param length map size.
     * @return array index.
     */
    private int indexFor(int h, int length) {
        return h & (length - 1);
    }

    @Override
    public Iterator<MapEntry<K, V>> iterator() {
        return new Iterator<MapEntry<K, V>>() {
            int iterModCount = modCount;
            int iterPosition = 0;

            @Override
            public boolean hasNext() {
                boolean result = false;
                for (int i = iterPosition; i < map.length; i++) {
                    if (map[i] != null) {
                        result = true;
                        break;
                    }
                    iterPosition++;
                }
                return result;
            }

            @Override
            public MapEntry<K, V> next() {
                if (iterModCount < modCount) {
                    throw new ConcurrentModificationException();
                }
                Optional<MapEntry<K, V>> result = Optional.empty();
                for (int i = iterPosition; i < map.length; i++) {
                    if (map[i] != null) {
                        result = Optional.of(map[i]);
                        iterPosition++;
                        break;
                    }
                    iterPosition++;
                }
                if (!result.isPresent()) {
                    throw new NoSuchElementException();
                }
                return result.get();
            }
        };
    }
}
