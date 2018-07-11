package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;

/**
 * Car body model for car shop web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Entity
@Table (name = "body")
@Data
@NoArgsConstructor
public class Body {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "id")
    @EqualsAndHashCode.Exclude
    private Integer id;

    @Column (name = "type")
    private String type;

    @Column (name = "color")
    private String color;

    @Column (name = "wheelSide")
    private String wheelSide;

    public Body(String type, String color, String wheelSide) {
        this.type = type;
        this.color = color;
        this.wheelSide = wheelSide;
    }

}
