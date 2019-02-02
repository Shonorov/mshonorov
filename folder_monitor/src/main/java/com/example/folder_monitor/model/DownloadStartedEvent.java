package com.example.folder_monitor.model;

import org.springframework.context.ApplicationEvent;

public class DownloadStartedEvent extends ApplicationEvent {

    private String fileName;

    public DownloadStartedEvent(EncodedFile source) {
        super(source);
        this.fileName = source.toString();
    }

    public String getFileName() {
        return fileName;
    }
}
