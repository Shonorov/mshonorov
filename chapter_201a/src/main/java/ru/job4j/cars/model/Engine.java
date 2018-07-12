package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;
/**
 * Engine model for car shop web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */

@Entity
@Table (name = "engine")
@Data
@NoArgsConstructor
public class Engine {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "id")
    @EqualsAndHashCode.Exclude
    private Integer id;

    @Column (name = "type")
    private String type;

    @Column (name = "volume")
    private Double volume;

    @Column (name = "power")
    private Integer power;

    @Column (name = "mileage")
    private Integer mileage;

    public Engine(String type, Double volume, Integer power, Integer mileage) {
        this.type = type;
        this.volume = volume;
        this.power = power;
        this.mileage = mileage;
    }
}
