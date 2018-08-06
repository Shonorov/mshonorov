package ru.job4j.web;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.ShortenedUrl;
import ru.job4j.domain.Url;
import ru.job4j.domain.UrlRegisterRequest;
import ru.job4j.repository.UrlRepository;
import ru.job4j.util.StringGenerator;

import java.util.HashMap;
import java.util.Map;


@Controller
public class UrlController {

    @Autowired
    private UrlRepository urlRepository;

    @PostMapping (value = "/register", consumes = {"application/json"}, produces = {"application/json"})
    @ResponseBody
    public ShortenedUrl registerUrl(@RequestBody UrlRegisterRequest request) {
        ShortenedUrl result;
        if (urlRepository.findById(request.getUrl()).isPresent()) {
            result = new ShortenedUrl(urlRepository.findById(request.getUrl()).get().getShorturl());
        } else {
            Url newUrl;
            if (request.getRedirectType().equals(301)) {
                 newUrl = new Url(request.getUrl(), StringGenerator.generateSting(6, false), 301, 0);
            } else {
                newUrl = new Url(request.getUrl(), StringGenerator.generateSting(6, false), 302, 0);
            }
            result = shortenUrl(request);
            urlRepository.save(newUrl);
        }
        return result;
    }

    private ShortenedUrl shortenUrl(UrlRegisterRequest request) {
        ShortenedUrl result = new ShortenedUrl("");
        String[] schemes = {"http"};
        UrlValidator validator = new UrlValidator(schemes);
        if (validator.isValid(request.getUrl())) {
            result.setShortUrl("http://short.com/" + StringGenerator.generateSting(6, false));
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
}
