package ru.job4j.model;
/**
 * Music style model for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Style {

    private String id;
    private String style;

    public Style(String id, String style) {
        this.id = id;
        this.style = style;
    }

    public String getId() {
        return id;
    }

    public String getStyle() {
        return style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Style)) {
            return false;
        }

        Style style1 = (Style) o;

        if (!id.equals(style1.id)) {
            return false;
        }
        return style.equals(style1.style);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + style.hashCode();
        return result;
    }
}
