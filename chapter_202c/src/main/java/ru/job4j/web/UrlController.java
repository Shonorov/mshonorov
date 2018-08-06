package ru.job4j.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UrlController {

    @PostMapping (value = "register")
    public String registerUrl() {
        return "";
    }

    @GetMapping (value = "/statistic/")
    public String getUserStatistic() {
        return "";
    }
}
