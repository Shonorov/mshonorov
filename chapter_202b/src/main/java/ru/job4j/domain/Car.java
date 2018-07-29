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
@Table (name = "cars")
@Data
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "id")
    @EqualsAndHashCode.Exclude
    private Integer id;

    @Column (name = "manufactured")
    private LocalDateTime manufactured;

    @Column (name = "drive")
    private String drive;

    @Column (name = "price")
    private Integer price;

    @Column (name = "photo")
    @EqualsAndHashCode.Exclude
    private byte[] photo;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "manufacturer_id", foreignKey = @ForeignKey(name = "Manufacturer_id_FK"))
    private Manufacturer manufacturer;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "model_id", foreignKey = @ForeignKey(name = "Model_id_FK"))
    private Model model;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "engine_id", foreignKey = @ForeignKey(name = "Engine_id_FK"))
    private Engine engine;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "body_id", foreignKey = @ForeignKey(name = "Body_id_FK"))
    private Body body;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "gearbox_id", foreignKey = @ForeignKey(name = "Gearbox_id_FK"))
    private GearBox gearbox;

    public Car(Integer id) {
        this.id = id;
    }

    public Car(LocalDateTime manufactured, String drive, Integer price) {
        this.manufactured = manufactured;
        this.drive = drive;
        this.price = price;
    }

}
