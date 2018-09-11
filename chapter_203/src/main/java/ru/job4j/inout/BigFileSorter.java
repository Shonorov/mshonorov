package ru.job4j.inout;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class BigFileSorter {

    public void sort(File source, File distance) throws FileNotFoundException {
        RandomAccessFile sourceFile = new RandomAccessFile(source, "r");
        RandomAccessFile targetFile = new RandomAccessFile(distance, "rw");
        
    }
}
