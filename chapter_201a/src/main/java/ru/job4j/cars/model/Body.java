package ru.job4j.cars.model;
/**
 * Car body model for car shop web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Body {

    private Integer id;
    private String type;
    private String color;
    private String wheelSide;

    public Body() {
    }

    public Body(String type, String color, String wheelSide) {
        this.type = type;
        this.color = color;
        this.wheelSide = wheelSide;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWheelSide() {
        return wheelSide;
    }

    public void setWheelSide(String wheelSide) {
        this.wheelSide = wheelSide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Body)) {
            return false;
        }

        Body body = (Body) o;

        if (!type.equals(body.type)) {
            return false;
        }
        if (!color.equals(body.color)) {
            return false;
        }
        return wheelSide.equals(body.wheelSide);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + color.hashCode();
        result = 31 * result + wheelSide.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Body{" + "id=" + id
                + ", type='" + type
                + ", color='" + color
                + ", wheelSide='" + wheelSide + '}';
    }
}
