package ru.job4j.cars.model;

import java.time.LocalDateTime;

/**
 * Trade item model for car shop web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Item {

    private Integer id;
    private String header;
    private String text;
    private User author;
    private LocalDateTime created;
    private boolean sold;
    private Car car;

    public Item() {
    }

    public Item(String header, String text) {
        this.header = header;
        this.text = text;
        this.created = LocalDateTime.now();
        this.sold = false;
    }

    public Item(String header, String text, User author, Car car) {
        this.header = header;
        this.text = text;
        this.author = author;
        this.created = LocalDateTime.now();
        this.sold = false;
        this.car = car;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }

        Item item = (Item) o;

        if (sold != item.sold) {
            return false;
        }
        if (!header.equals(item.header)) {
            return false;
        }
        if (!text.equals(item.text)) {
            return false;
        }
        if (!author.equals(item.author)) {
            return false;
        }
        if (!car.equals(item.car)) {
            return false;
        }
        return created.equals(item.created);
    }

    @Override
    public int hashCode() {
        int result = text.hashCode();
        result = 31 * result + header.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + created.hashCode();
        result = 31 * result + car.hashCode();
        result = 31 * result + (sold ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id
                + ", header='" + header
                + ", text='" + text
                + ", author=" + author
                + ", car=" + car
                + ", created=" + created
                + ", sold=" + sold + '}';
    }
}
