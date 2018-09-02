package ru.job4j.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.job4j.domain.Url;
import ru.job4j.repository.UrlRepository;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RedirectController {

    @Autowired
    private UrlRepository urlRepository;

    @GetMapping(value = "/redirect_page")
    public String showRedirectPage() {
        return "redirect";
    }


    @GetMapping(value = "/redirect")
    public RedirectView proxyRedirect(@RequestParam("url") String url) {
        System.out.println(url);
        RedirectView rv = new RedirectView(url);
        if (urlRepository.findById(url).isPresent()) {
            Url redirect = urlRepository.findById(url).get();
            System.out.println(redirect);
            rv = new RedirectView(redirect.getShorturl());
            rv.setStatusCode(HttpStatus.valueOf(redirect.getRedirecttype()));
            redirect.setCount(redirect.getCount() + 1);
            urlRepository.save(redirect);
        }
        return rv;
    }
}
