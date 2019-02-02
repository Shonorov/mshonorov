package com.example.folder_monitor.service;

import com.example.folder_monitor.model.DownloadFinishedEvent;
import com.example.folder_monitor.model.DownloadStartedEvent;
import com.example.folder_monitor.model.EncodedFile;
import com.example.folder_monitor.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Configuration
@EnableScheduling
public class Scheduler {

    @Value("${lookup.folder}")
    private String path;

    @Autowired
    private FileManager fileManager;

    @Scheduled (fixedRate = 3000)
    public void folderLookup() {
        File folder = new File(path);
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                fileManager.copyAndSave(fileEntry);
            }
        }
    }
}
