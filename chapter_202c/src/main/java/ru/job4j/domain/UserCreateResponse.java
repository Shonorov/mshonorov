package ru.job4j.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
public class UserCreateResponse {

    private Boolean success;
    private String description;
    private String password;
}
