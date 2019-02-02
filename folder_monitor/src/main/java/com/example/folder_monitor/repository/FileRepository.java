package com.example.folder_monitor.repository;

import com.example.folder_monitor.model.EncodedFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<EncodedFile, Long> {
}
