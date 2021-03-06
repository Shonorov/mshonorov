package ru.job4j.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table (name ="user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column (name = "username")
    private String username;

    @Column (name = "password")
    private String password;
}
