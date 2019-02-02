package com.example.folder_monitor.config;

import com.example.folder_monitor.model.DownloadStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DownloadStartedListener implements ApplicationListener<DownloadStartedEvent> {

    @Override
    public void onApplicationEvent(DownloadStartedEvent downloadStartedEvent) {
        System.out.println("Download started " + downloadStartedEvent.getFileName() + " " + Thread.currentThread().getName());
    }
}
