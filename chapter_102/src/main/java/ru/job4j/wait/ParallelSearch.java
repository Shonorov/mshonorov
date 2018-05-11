package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Multithreaded keyword files search.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class ParallelSearch {
    /**
     * Root directory search path.
     * Text to find.
     * List of file extensions.
     * If file search finished.
     * Queue of found files in root directory.
     * List of files with found text.
     */
    private final String root;
    private final String text;
    private final List<String> exts;
    private volatile boolean finish = false;
    private final Queue<String> files = new ConcurrentLinkedQueue<>();
    private final List<String> paths = Collections.synchronizedList(new ArrayList<>());

    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    public List<String> getPaths() {
        return paths;
    }

    /**
     * Start multithreaded directory lookup and search.
     */
    public void init() {
        Thread search = new Thread() {
            @Override
            public void run() {
                result();
                finish = true;
            }
        };
        Thread read = new Thread() {
            @Override
            public void run() {
                while (!finish) {
                    read();
                }
            }
        };
        search.start();
        read.start();
        try {
            search.join();
            read.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Find all files in root directory.
     */
    private void result() {
        Path rootPath = Paths.get(root);
        try {
            Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String current = file.toAbsolutePath().toString();
                    for (String ext : exts) {
                        if (current.endsWith(ext)) {
                            files.add(current);
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find files with given word.
     */
    private void read() {
        while (files.size() != 0) {
            try {
                Path current = Paths.get(files.poll());
                String content = new String(Files.readAllBytes(current));
                if (content.contains(text)) {
                    paths.add(current.toAbsolutePath().toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
