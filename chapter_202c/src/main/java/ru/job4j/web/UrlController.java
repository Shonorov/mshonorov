package ru.job4j.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.ShortenedUrl;
import ru.job4j.domain.Url;
import ru.job4j.domain.UrlRegisterRequest;
import ru.job4j.repository.UrlRepository;
import ru.job4j.util.StringGenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Controller
public class UrlController {

    @Autowired
    private UrlRepository urlRepository;

    @PostMapping (value = "/register", consumes = {"application/json"}, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity registerUrl(@RequestBody String json) {
        ResponseEntity responseEntity;
        ShortenedUrl result;
        UrlRegisterRequest request = new UrlRegisterRequest();
        try {
            request = new ObjectMapper().readValue(json, UrlRegisterRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (urlRepository.findById(request.getUrl()).isPresent()) {
            result = new ShortenedUrl(urlRepository.findById(request.getUrl()).get().getShorturl());
            responseEntity = new ResponseEntity(result, HttpStatus.CONFLICT);
        } else {
            Url newUrl;
            if (request.getRedirectType().equals(301)) {
                 newUrl = new Url(request.getUrl(), shortenUrl(request), 301, 0);
            } else {
                newUrl = new Url(request.getUrl(), shortenUrl(request), 302, 0);
            }
            result = new ShortenedUrl(newUrl.getShorturl());
            responseEntity = new ResponseEntity(result, HttpStatus.CREATED);
            urlRepository.save(newUrl);
        }
        return responseEntity;
    }

    /**
     * Convert Url register request to shortened url string.
     * @param request url request json object.
     * @return shortened url.
     */
    private String shortenUrl(UrlRegisterRequest request) {
        String result = "";
        String[] schemes = {"http"};
        UrlValidator validator = new UrlValidator(schemes);
        if (validator.isValid(request.getUrl())) {
            result = "http://short.com/" + StringGenerator.generateSting(6, false);
        }
        return result;
    }


    @GetMapping (value = "/statistic/{AccountId}", produces = {"application/json"})
    public Map<String, Integer> getUserStatistic(@PathVariable(value = "AccountId") String accountId) {
        Map<String, Integer> result = new HashMap<>();
        for (Url url : urlRepository.findAll()) {
            result.put(url.getUrl(), url.getCount());
        }
        return result;
    }

    @GetMapping (value = "/redirect/{url}")
    public void redirect(@PathVariable(value = "url") String url1) {
        System.out.println("!!!!" + url1);
    }
}
