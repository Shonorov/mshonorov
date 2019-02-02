package com.example.folder_monitor.config;

import com.example.folder_monitor.model.DownloadFinishedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DownloadFinishedListener implements ApplicationListener<DownloadFinishedEvent> {

    @Override
    public void onApplicationEvent(DownloadFinishedEvent downloadFinishedEvent) {
        System.out.println("Download finished " + downloadFinishedEvent.getFileName() + " " + Thread.currentThread().getName());
    }
}
