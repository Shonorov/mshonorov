package ru.job4j.xmlxslt;
/**
 * SQLIte JDBC, JAXB, XSLT demo application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleParser {

    private String dbname;
    private int count;

    public SimpleParser(String dbname, int count) {
        this.dbname = dbname;
        this.count = count;
    }

    /**
     * Start application.
     */
    public void start() {
        SQLiteStorage storage = new SQLiteStorage(dbname, count);
        XMLConverter converter = new XMLConverter(storage.process());
        converter.convertXML();
        converter.changeStyle();
        System.out.print(converter.sum());
    }
}
