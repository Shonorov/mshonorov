package ru.job4j.domain;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class NewAccount {

    private String AccountId;
}
