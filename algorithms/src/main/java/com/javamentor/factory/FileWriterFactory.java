package com.javamentor.factory;

import org.w3c.dom.Document;
import java.io.File;

/**
 * Factory pattern example.
 */
public class FileWriterFactory {

    /**
     * Get file writer type by file type.
     * @param file input file.
     * @return file writer.
     */
    public AbstractFileWriter getWriter(Object file) {
        AbstractFileWriter writer = null;
        if (file instanceof Document) {
            writer = new HTMLFileWriter();
        } else if (file instanceof File) {
            writer = new XMLFileWriter();
        }
        return writer;
    }
}
