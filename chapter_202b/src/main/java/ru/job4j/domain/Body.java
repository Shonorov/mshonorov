package ru.job4j.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    @Column (name = "wheelside")
    private String wheelside;

    public Body(String type, String color, String wheelside) {
        this.type = type;
        this.color = color;
        this.wheelside = wheelside;
    }

}
