package com.example.folder_monitor.model;

import org.springframework.context.ApplicationEvent;

public class FileConvertedEvent extends ApplicationEvent {

    private String fileName;

    public FileConvertedEvent(Object source) {
        super(source);
        this.fileName = source.toString();
    }

    public String getFileName() {
        return fileName;
    }
}
