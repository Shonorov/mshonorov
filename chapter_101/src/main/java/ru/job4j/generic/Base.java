package ru.job4j.generic;
/**
 * Base type for objects stored.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public abstract class Base {
    /**
     * Base object identifier.
     */
    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }
}
