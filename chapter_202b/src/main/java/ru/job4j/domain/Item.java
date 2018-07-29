package ru.job4j.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Trade item model for car shop web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */

@Entity
@Table (name = "items")
@Data
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "id")
    @EqualsAndHashCode.Exclude
    private Integer id;

    @Column (name = "header")
    private String header;

    @Column (name = "text")
    private String text;

    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn (name = "author_id", foreignKey = @ForeignKey(name = "User_id_FK"))
    private User author;

    @Column (name = "created")
    private LocalDateTime created;

    @Column (name = "sold")
    @EqualsAndHashCode.Exclude
    private boolean sold;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "car_id", foreignKey = @ForeignKey(name = "Car_id_FK"))
    private Car car;

    public Item(Integer id) {
        this.id = id;
    }

    public Item(String header, String text) {
        this.header = header;
        this.text = text;
        this.created = LocalDateTime.now();
        this.sold = false;
    }

    public Item(String header, String text, User author, Car car) {
        this.header = header;
        this.text = text;
        this.author = author;
        this.created = LocalDateTime.now();
        this.sold = false;
        this.car = car;
    }
}
