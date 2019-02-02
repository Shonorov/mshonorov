package com.example.folder_monitor.model;

import org.springframework.context.ApplicationEvent;

public class DownloadFinishedEvent extends ApplicationEvent {

    private String fileName;

    public DownloadFinishedEvent(Object source) {
        super(source);
        this.fileName = source.toString();
    }

    public String getFileName() {
        return fileName;
    }
}
