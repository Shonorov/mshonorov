package ru.job4j.cars.model;

import lombok.*;

import java.util.List;
import javax.persistence.*;

/**
 * Car manufacturer model for car shop web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */

@Entity
@Table (name = "manufacturer")
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Getter @Setter
public class Manufacturer {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "id")
    @EqualsAndHashCode.Exclude
    private Integer id;

    @Column (name = "name")
    private String name;

    @Column (name = "country")
    private String country;

    @Column (name = "models")
    @OneToMany
    @EqualsAndHashCode.Exclude
    private List<Model> models;

    public Manufacturer(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
