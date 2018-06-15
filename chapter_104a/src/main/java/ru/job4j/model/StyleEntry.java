package ru.job4j.model;

import java.time.LocalDateTime;

/**
 * Music style entry for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class StyleEntry {

    private String id;
    private String userID;
    private String styleID;

    public StyleEntry(String id, String userID, String styleID) {
        this.id = id;
        this.userID = userID;
        this.styleID = styleID;
    }

    public StyleEntry(String userID, String styleID) {
        this.id = LocalDateTime.now().toString() + (short) (Math.random() * 100);
        this.userID = userID;
        this.styleID = styleID;
    }

    public String getId() {
        return id;
    }

    public String getUserID() {
        return userID;
    }

    public String getStyleID() {
        return styleID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StyleEntry)) {
            return false;
        }

        StyleEntry that = (StyleEntry) o;

        if (!id.equals(that.id)) {
            return false;
        }
        if (!userID.equals(that.userID)) {
            return false;
        }
        return styleID.equals(that.styleID);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userID.hashCode();
        result = 31 * result + styleID.hashCode();
        return result;
    }
}
