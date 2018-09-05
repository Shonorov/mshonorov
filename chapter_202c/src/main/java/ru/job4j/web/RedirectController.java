package ru.job4j.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.domain.Url;
import ru.job4j.repository.UrlRepository;

@Controller
public class RedirectController {

    @Autowired
    private UrlRepository urlRepository;

    @GetMapping(value = "/redirect_page")
    public String showRedirectPage() {
        return "redirect";
    }


    @GetMapping(value = "/redirect")
    public ResponseEntity proxyRedirect(@RequestParam("url") String url) {
        Url redirect = new Url();
        if (urlRepository.findById(url).isPresent()) {
            redirect = urlRepository.findById(url).get();
            redirect.setCount(redirect.getCount() + 1);
            urlRepository.save(redirect);
        }
        return new ResponseEntity(redirect, HttpStatus.OK);
    }
}
