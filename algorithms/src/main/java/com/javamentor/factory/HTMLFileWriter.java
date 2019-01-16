package com.javamentor.factory;

public class HTMLFileWriter extends AbstractFileWriter {

    @Override
    public Object writeFile(Object file) {
        System.out.println("HTML file " + file + " saved!");
        return file;
    }
}
