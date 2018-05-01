package ru.job4j.xmlxslt;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Root class with EntryAttribute list for JAXB unmarshalling modified XML.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntriesAttribute {

    @XmlElement(name = "entry")
    private List<EntryAttribute> entries = null;

    public List<EntryAttribute> getEntries() {
        return entries;
    }
}
