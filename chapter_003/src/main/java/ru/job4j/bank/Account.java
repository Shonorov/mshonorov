package ru.job4j.bank;
/**
 * Account in bank class.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Account {
    /**
     * Money value.
     * Account requisites.
     */
    private Double value;
    private String requisites;

    public Account(Double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public Double getValue() {
        return value;
    }

    public String getRequisites() {
        return requisites;
    }

    /**
     * Plus or minus money.
     * @param value money value.
     */
    public void addValue(Double value) {
        this.value += value;
    }
}
