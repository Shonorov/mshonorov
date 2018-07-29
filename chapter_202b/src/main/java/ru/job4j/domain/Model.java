package ru.job4j.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column (name = "releasedate")
    private LocalDateTime releasedate;

    @Column (name = "manufacturing")
    private Boolean manufacturing;

    public Model(String name, LocalDateTime releasedate, Boolean manufacturing) {
        this.name = name;
        this.releasedate = releasedate;
        this.manufacturing = manufacturing;
    }
}
