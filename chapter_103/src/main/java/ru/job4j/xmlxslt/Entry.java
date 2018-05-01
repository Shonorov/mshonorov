package ru.job4j.xmlxslt;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entry class for JAXB marshalling.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
 public class Entry {

    private int field;

    public void setField(int field) {
        this.field = field;
    }
}
