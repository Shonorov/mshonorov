package ru.job4j.web;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.ShortenedUrl;
import ru.job4j.domain.Url;
import ru.job4j.domain.UrlRegisterRequest;
import ru.job4j.repository.UrlRepository;
import ru.job4j.repository.UserRepository;
import ru.job4j.util.StringGenerator;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@Controller
public class UrlController {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping (value = "/register")
    @ResponseBody
    public ResponseEntity registerUrl(@RequestBody UrlRegisterRequest request) {
        ResponseEntity responseEntity;
        ShortenedUrl result;
        if (urlRepository.findById(request.getUrl()).isPresent()) {
            result = new ShortenedUrl(urlRepository.findById(request.getUrl()).get().getShorturl());
            responseEntity = new ResponseEntity(result, HttpStatus.CONFLICT);
        } else {
            Url newUrl;
            if ((request.getRedirectType() != null) && (request.getRedirectType().equals(301))) {
                newUrl = new Url(request.getUrl(), shortenUrl(request), 301, 0L);
            } else {
                newUrl = new Url(request.getUrl(), shortenUrl(request), 302, 0L);
            }
            result = new ShortenedUrl(newUrl.getShorturl());
            responseEntity = new ResponseEntity(result, HttpStatus.CREATED);
            urlRepository.save(newUrl);
        }
        return responseEntity;
    }

    @GetMapping(value = "/register")
    public String accountFormRedirect() {
        return "register";
    }

    /**
     * Convert Url register request to shortened url string.
     * @param request url request json object.
     * @return shortened url.
     */
    private String shortenUrl(UrlRegisterRequest request) {
        String result = "";
        String[] schemes = {"http", "https"};
        UrlValidator validator = new UrlValidator(schemes);
        if (validator.isValid(request.getUrl())) {
            result = "http://short.com/" + StringGenerator.generateSting(6, false);
        }
        return result;
    }

    @GetMapping (value = "/statistic/{AccountId}")
    public @ResponseBody ResponseEntity<Map<String, Long>> showStatistic(@PathVariable(value = "AccountId") String accountId, HttpServletResponse response) {
        if (userRepository.findById(accountId).isPresent()) {
            String  auth = accountId + ":" + userRepository.findById(accountId).get().getPassword();
            String encoding = Base64.getEncoder().encodeToString(auth.getBytes());
            response.setHeader("Authorization", "Basic " + encoding);
        }
        Map<String, Long> result = new HashMap<>();
        for (Url url : urlRepository.findAll()) {
            result.put(url.getUrl(), url.getCount());
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping (value = "/statistic")
    public String redirect(Principal principal, Model model) {
        model.addAttribute("AccountId", principal.getName());

        return "statistic";
    }

    @GetMapping (value = "/help")
    public String help() {
        return "help";
    }
}
