package ru.job4j.wait;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ParallelSearchTest {

    private final String root = "C:\\Windows\\Temp";
    private final String find = "Error";
    private final List<String> extensions = new ArrayList<>(Arrays.asList(".txt", ".log"));

    @Before
    public void createLog() {
        String filePath = root + "\\Test.log";
        Path path = Paths.get(filePath);
        try {
            if (!Files.exists(path)) {
                Files.createFile(Paths.get(filePath));
            }
            Files.write(path, find.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenWalkThenFindNotNull() {
        ParallelSearch search = new ParallelSearch(root, find, extensions);
        search.init();
        assertThat(search.getPaths().size(), is(notNullValue()));
    }
}
