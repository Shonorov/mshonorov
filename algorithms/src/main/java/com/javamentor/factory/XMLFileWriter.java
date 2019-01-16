package com.javamentor.factory;

public class XMLFileWriter extends AbstractFileWriter {

    @Override
    public Object writeFile(Object file) {
        System.out.println("XML file " + file + " saved!");
        return file;
    }
}
