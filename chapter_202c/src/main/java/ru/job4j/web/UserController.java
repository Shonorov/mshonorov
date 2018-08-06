package ru.job4j.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.NewAccount;
import ru.job4j.domain.UserCreateResponse;
import ru.job4j.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping (value = "/account", consumes = {"application/json"}, produces = {"application/json"})
    @ResponseBody
    public UserCreateResponse registerAccount(@RequestBody NewAccount account) {
        UserCreateResponse response;
        if (userRepository.findById(account.getAccountId()).isPresent()) {
            response = new UserCreateResponse(true, "Your account is opened", "QWEQWE");
        } else {
            response = new UserCreateResponse(false, "Account with that ID already exists", "");
        }
        return response;
    }


}
