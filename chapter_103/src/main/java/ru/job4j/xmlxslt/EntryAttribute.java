package ru.job4j.xmlxslt;

import javax.xml.bind.annotation.*;

/**
 * Entry class for JAXB unmarshalling modified XML.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryAttribute {

        @XmlAttribute
        private int field;

        public int getField() {
            return field;
        }
}
