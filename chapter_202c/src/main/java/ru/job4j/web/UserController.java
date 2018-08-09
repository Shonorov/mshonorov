package ru.job4j.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.NewAccountRequest;
import ru.job4j.domain.User;
import ru.job4j.domain.UserCreateResponse;
import ru.job4j.repository.UserRepository;
import ru.job4j.util.StringGenerator;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping (value = "/account", consumes = {"application/json"}, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity registerAccount(@RequestBody String json) {
        System.out.println(json);
        UserCreateResponse response;
        ResponseEntity responseEntity;
        NewAccountRequest account = new NewAccountRequest();
        try {
            account = new ObjectMapper().readValue(json, NewAccountRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (userRepository.findById(account.getAccountId()).isPresent()) {
            response = new UserCreateResponse(false, "Account with that ID already exists", "");
            responseEntity = new ResponseEntity(response, HttpStatus.CONFLICT);
        } else {
            response = new UserCreateResponse(true, "Your account is opened", StringGenerator.generateSting(8, true));
            userRepository.save(new User(account.getAccountId(), response.getPassword()));
            responseEntity = new ResponseEntity(response, HttpStatus.CREATED);
        }
        return responseEntity;
    }

    @GetMapping (value = "/account")
    public String accountFormRedirect() {
        return "account";
    }

}
