package com.example.folder_monitor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;

@Entity
public class EncodedFile {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "file")
    private File file;

    public EncodedFile(File file) {
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return file.getName();
    }
}
