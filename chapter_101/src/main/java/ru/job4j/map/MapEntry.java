package ru.job4j.map;
/**
 * Entry for SimpleHashMap.
 * @param <K> generic key.
 * @param <V> generic value.
 */
public class MapEntry<K, V> {
    private K key;
    private V value;

    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
