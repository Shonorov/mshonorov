package ru.job4j.xmlxslt;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.ArrayList;

/**
 * Convert and parse XML data.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class XMLConverter {

    private static final String FILENAME1 = "1.xml";
    private static final String FILENAME2 = "2.xml";
    private static final String FILENAME3 = "xml1toxml2.xsl";
    private int[] data;

    public XMLConverter(int[] data) {
        this.data = data;
    }

    /**
     * Create and fill Entries class from array.
     * @return filled Entries class.
     */
    private Entries fill() {
        Entries result = new Entries();
        result.setEntries(new ArrayList<>());
        for (int i = 0; i < data.length; i++) {
            Entry current = new Entry();
            current.setField(data[i]);
            result.getEntries().add(current);
        }
        return result;
    }

    /**
     * Convert array of values to XML file.
     */
    public void convertXML() {
        try {
            File file = new File(FILENAME1);
            Entries entries = fill();
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(entries, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Change style of the XLS file with XSL transformer.
     */
    public void changeStyle() {
        StreamSource xslcode = new StreamSource(new File(FILENAME3));
        StreamSource input = new StreamSource(new File(FILENAME1));
        StreamResult output = new StreamResult(new File(FILENAME2));

        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer trans = tf.newTransformer(xslcode);
            trans.transform(input, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Unmarshall modified XML with custom class and count sum of elements.
     * @return sum of XML elements.
     */
    public long sum() {
        long result = 0;
        try {
            File file = new File(FILENAME2);
            JAXBContext jaxbContext = JAXBContext.newInstance(EntriesAttribute.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            EntriesAttribute entries = (EntriesAttribute) jaxbUnmarshaller.unmarshal(file);
            for (EntryAttribute entry : entries.getEntries()) {
                result += entry.getField();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
