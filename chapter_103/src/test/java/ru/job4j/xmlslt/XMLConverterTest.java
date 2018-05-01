package ru.job4j.xmlslt;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.xmlxslt.XMLConverter;
import java.io.File;
import java.io.IOException;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class XMLConverterTest {

    @Test
    public void whenConvertThenEqualObjects() throws IOException {
        int[] input = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        XMLConverter converter = new XMLConverter(input);
        converter.convertXML();
        File expect = new File("src\\test\\java\\ru\\job4j\\xmlslt\\1.xml");
        File result = new File("C:\\Windows\\temp\\1.xml");
        Assert.assertEquals(FileUtils.readLines(expect), FileUtils.readLines(result));
        converter.changeStyle();
        File expect2 = new File("src\\test\\java\\ru\\job4j\\xmlslt\\2.xml");
        File result2 = new File("C:\\Windows\\temp\\2.xml");
        Assert.assertEquals(FileUtils.readLines(expect2), FileUtils.readLines(result2));
    }
}
