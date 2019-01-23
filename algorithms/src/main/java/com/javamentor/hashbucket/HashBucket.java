package com.javamentor.hashbucket;

public class HashBucket {

    public int hash(Object key) {
        int h = key.hashCode();
        return (key == null) ? 0 : h ^ (h >>> 16);
    }

    public int bucket(int hash, int n) {
       return  (n - 1) & hash;
    }
}
