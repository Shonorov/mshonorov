package ru.job4j.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.User;
import ru.job4j.domain.UserCreateResponse;
import ru.job4j.repository.UserRepository;
import ru.job4j.util.StringGenerator;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping (value = "/account")
    @ResponseBody
    public ResponseEntity registerAccount(@RequestBody Map<String, String> map) {
        String id = map.get("AccountId");
        UserCreateResponse response;
        ResponseEntity responseEntity;
        if (userRepository.findById(id).isPresent()) {
            response = new UserCreateResponse(false, "Account with that ID already exists", "");
            responseEntity = new ResponseEntity(response, HttpStatus.CONFLICT);
        } else {
            response = new UserCreateResponse(true, "Your account is opened", StringGenerator.generateSting(8, true));
            userRepository.save(new User(id, response.getPassword()));
            responseEntity = new ResponseEntity(response, HttpStatus.CREATED);
        }
        return responseEntity;
    }

    @GetMapping(value = "/account")
    public String accountFormRedirect() {
        return "account";
    }

}
