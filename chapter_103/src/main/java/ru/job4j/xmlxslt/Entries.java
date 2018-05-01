package ru.job4j.xmlxslt;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Root class with Entry list for JAXB marshalling.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
 public class Entries {

    @XmlElement(name = "entry")
    private List<Entry> entries = null;

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public List<Entry> getEntries() {
        return entries;
    }
}
