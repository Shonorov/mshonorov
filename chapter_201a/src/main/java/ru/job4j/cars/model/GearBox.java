package ru.job4j.cars.model;
/**
 * Gearbox model for car shop web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GearBox {

    private Integer id;
    private String type;
    private Integer gearCount;

    public GearBox() {
    }

    public GearBox(String type, Integer gearCount) {
        this.type = type;
        this.gearCount = gearCount;
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

    public Integer getGearCount() {
        return gearCount;
    }

    public void setGearCount(Integer gearCount) {
        this.gearCount = gearCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GearBox)) {
            return false;
        }

        GearBox gearBox = (GearBox) o;

        if (!type.equals(gearBox.type)) {
            return false;
        }
        return gearCount.equals(gearBox.gearCount);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + gearCount.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "GearBox{" + "id=" + id
                + ", type='" + type
                + ", gearCount=" + gearCount + '}';
    }
}
