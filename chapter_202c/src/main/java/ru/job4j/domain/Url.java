package ru.job4j.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "url")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Url {

    @Id
    @Column (name = "url")
    private String url;

    @Column (name = "shorturl")
    private String shorturl;

    @Column (name = "redirecttype")
    private Integer redirecttype;

    @Column (name = "count")
    private Long count;
}
