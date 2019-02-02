package com.example.folder_monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import java.io.File;

@Configuration
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
