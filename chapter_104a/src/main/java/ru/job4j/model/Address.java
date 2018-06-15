package ru.job4j.model;

import java.time.LocalDateTime;

/**
 * User address model for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Address {

    private String id;
    private String address;

    public Address(String id, String address) {
        this.id = id;
        this.address = address;
    }

    public Address(String address) {
        this.id = LocalDateTime.now().toString() + (short) (Math.random() * 100);
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }

        Address address1 = (Address) o;

        if (!id.equals(address1.id)) {
            return false;
        }
        return address.equals(address1.address);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }
}
