package ru.job4j.tracker;
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
    String id;
    String name;
    String desc;
    long created;
    String[] comments;

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

    public String getName() {
        return name;
    }
}
