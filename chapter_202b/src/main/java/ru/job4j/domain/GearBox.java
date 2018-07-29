package ru.job4j.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Gearbox model for car shop web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */

@Entity
@Table (name = "gearbox")
@Data
@NoArgsConstructor
public class GearBox {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "id")
    @EqualsAndHashCode.Exclude
    private Integer id;

    @Column (name = "type")
    private String type;

    @Column (name = "gearcount")
    private Integer gearCount;

    public GearBox(String type, Integer gearCount) {
        this.type = type;
        this.gearCount = gearCount;
    }
}
