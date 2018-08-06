package ru.job4j.domain;

import lombok.Data;

@Data
public class UrlRegisterRequest {

    private String url;
    private Integer redirectType;
}
