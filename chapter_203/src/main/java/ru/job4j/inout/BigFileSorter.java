package ru.job4j.inout;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class BigFileSorter {

    public void sort(File source, File distance) {
        try (
            RandomAccessFile sourceFile = new RandomAccessFile(source, "r");
            RandomAccessFile targetFile = new RandomAccessFile(distance, "rw")
        ) {
            Map<Long, Integer> lineMap = new HashMap<>();
            long length = sourceFile.length();
            long position = 0;
            int min = Integer.MAX_VALUE;
            do {
                String temp = sourceFile.readLine();
                lineMap.put(position, temp.length());
                min = temp.length() < min ? temp.length() : min;
                position = sourceFile.getFilePointer();
            } while (position < length);

            while (lineMap.size() != 0) {
                if (lineMap.values().contains(min)) {
                    for (Map.Entry<Long, Integer> entry : lineMap.entrySet()) {
                        if (entry.getValue() == min) {
                            sourceFile.seek(entry.getKey());
                            String line = sourceFile.readLine();
                            targetFile.writeBytes(line + System.lineSeparator());
                            lineMap.remove(entry.getKey());
                            break;
                        }
                    }
                } else {
                    min++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
