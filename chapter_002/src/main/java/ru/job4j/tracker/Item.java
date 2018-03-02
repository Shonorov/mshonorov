package ru.job4j.tracker;

import java.time.LocalTime;

/**
 * Items management in tracker.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Item {
    /**
     * id - time plus random number.
     * name - item name.
     * desc - description.
     * created - creation date.
     * comments - comments to items by users.
     */
    private String id;
    private String name;
    private String desc;
    private long created;
    private String[] comments;

    public Item() {
    }

    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public Item(String name, String desc, long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }
    /**
     * Returns string with item properties for console output.
     * @return formated string.
     */
    public String print() {
        return String.format("%s\t%s\t%s\t%s", this.id, this.name, this.desc, this.created);
    }
}
