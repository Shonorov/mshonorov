package ru.job4j.todolist;

import java.time.LocalDateTime;

/**
 * Item model for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Item {

    private Integer id;
    private String description;
    private LocalDateTime createdate;
    private boolean done;

    public Item() {
        this.createdate = LocalDateTime.now();
        this.done = false;
    }

    public Item(String description) {
        this.description = description;
        this.createdate = LocalDateTime.now();
        this.done = false;
    }

    public Item(Integer id, String description, LocalDateTime createdate, boolean done) {
        this.id = id;
        this.description = description;
        this.createdate = createdate;
        this.done = done;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedate() {
        return createdate;
    }

    public void setCreatedate(LocalDateTime createdate) {
        this.createdate = createdate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Item{"
                + "id='" + id + '\''
                + ", description='" + description + '\''
                + ", createdate=" + createdate
                + ", done=" + done
                + '}';
    }
}
