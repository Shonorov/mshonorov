package com.javamentor.hashbucket;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class HashBucketTest {

    @Test
    public void whenHashThenNotMod() {
        HashBucket hashBucket = new HashBucket();
        int size = 16;
        for (int i = -10; i < 10; i++) {
            int hash = hashBucket.hash(i);
            int bucket = hashBucket.bucket(hashBucket.hash(i), size);
            int mod = hash % size;
            System.out.println("Bucket: " + bucket + " Mod: " + mod);
//            assertThat(bucket, is(mod));
        }
    }
}