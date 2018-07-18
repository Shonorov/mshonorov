package ru.job4j.cars.model;

import lombok.*;

import java.time.LocalDateTime;
import javax.persistence.*;
/**
 * Car model for car shop web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Entity
@Table (name = "model")
@Data
@NoArgsConstructor
public class Model {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "id")
    @EqualsAndHashCode.Exclude
    private Integer id;

    @Column (name = "name")
    private String name;

    @Column (name = "releaseDate")
    private LocalDateTime releaseDate;

    @Column (name = "manufacturing")
    private Boolean manufacturing;

    public Model(Integer id) {
        this.id = id;
    }

    public Model(String name, LocalDateTime releaseDate, Boolean manufacturing) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.manufacturing = manufacturing;
    }
}
