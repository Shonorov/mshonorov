package ru.job4j.inout;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class BigFileSorter {

    public void sort(File source, File distance) throws IOException {
        RandomAccessFile sourceFile = new RandomAccessFile(source, "r");
        RandomAccessFile targetFile = new RandomAccessFile(distance, "rw");
        Map<Long, Integer> lineMap = new HashMap<>();
        long length = sourceFile.length();
        long position = 0;
        System.out.println("!!!  " + length);
        while(position < length) {
            String temp = sourceFile.readLine();
//            System.out.println(temp);
            lineMap.put(sourceFile.getFilePointer(), temp.length());
            position += sourceFile.getFilePointer();

            System.out.println(position);
        }
        System.out.println(lineMap);
//        sourceFile.seek(85L);
//        System.out.println(sourceFile.readLine());
//        System.out.println(sourceFile.readLine().length());
    }
}
