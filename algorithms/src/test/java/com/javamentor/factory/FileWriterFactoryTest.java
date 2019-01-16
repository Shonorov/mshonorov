package com.javamentor.factory;

import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.Is.is;
public class FileWriterFactoryTest {

    @Test
    public void whenObjectIsDocumentThenReturnHTMLFileWriter() throws ParserConfigurationException {
        FileWriterFactory factory = new FileWriterFactory();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document file = db.newDocument();
        AbstractFileWriter object = factory.getWriter(file);
        assertThat(object, instanceOf(HTMLFileWriter.class));
        assertThat(object.writeFile(file), is(file));
    }

    @Test
    public void whenObjectIsFileThenReturnXMLFileWriter() {
        FileWriterFactory factory = new FileWriterFactory();
        File file = new File("temp.xml");
        AbstractFileWriter object = factory.getWriter(file);
        assertThat(object, instanceOf(XMLFileWriter.class));
        assertThat(object.writeFile(file), is(file));
    }
}