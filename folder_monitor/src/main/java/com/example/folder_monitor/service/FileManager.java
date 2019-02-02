package com.example.folder_monitor.service;

import com.example.folder_monitor.model.DownloadFinishedEvent;
import com.example.folder_monitor.model.DownloadStartedEvent;
import com.example.folder_monitor.model.EncodedFile;
import com.example.folder_monitor.model.FileConvertedEvent;
import com.example.folder_monitor.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class FileManager {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private FileRepository repository;

    @Async(value = "myexecutor")
    @Transactional(propagation = Propagation.REQUIRED)
    public void copyAndSave(File file) {
        EncodedFile sourceFile = new EncodedFile(file);
        eventPublisher.publishEvent(new DownloadStartedEvent(sourceFile));
        File dest = new File(System.getProperty("java.io.tmpdir")  + file.getName());
        try {
            Files.deleteIfExists(dest.toPath());
            Files.move(file.toPath(), dest.toPath());
            EncodedFile destFile = new EncodedFile(dest);
            convert(dest);
            repository.save(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        eventPublisher.publishEvent(new DownloadFinishedEvent(sourceFile));
    }

    private void convert(File file) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = img.getWidth();
        int height = img.getHeight();
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int p = img.getRGB(x,y);
                int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;
                //calculate average
                int avg = (r+g+b)/3;
                //replace RGB value with avg
                p = (a<<24) | (avg<<16) | (avg<<8) | avg;
                img.setRGB(x, y, p);
            }
        }

        try {
            ImageIO.write(img, "jpg", file);
            eventPublisher.publishEvent(new FileConvertedEvent(new EncodedFile(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
