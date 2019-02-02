package com.example.folder_monitor.config;

import com.example.folder_monitor.model.FileConvertedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class FileConvertedListener implements ApplicationListener<FileConvertedEvent> {

    @Override
    public void onApplicationEvent(FileConvertedEvent fileConvertedEvent) {
        System.out.println("File converted - " + fileConvertedEvent.getFileName() + " " + Thread.currentThread().getName());
    }
}
