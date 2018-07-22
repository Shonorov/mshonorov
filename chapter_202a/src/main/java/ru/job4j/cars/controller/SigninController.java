package ru.job4j.cars.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.cars.config.HibernateConfig;
import ru.job4j.cars.config.SpringDataConfig;
import ru.job4j.cars.dao.CarsRepository;
import ru.job4j.cars.dao.ManufacturerDataRepository;
import ru.job4j.cars.dao.UserDataRepository;
import ru.job4j.cars.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

/**
 * User sign in spring controller.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Controller
public class SigninController {

//    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
//    private CarsRepository repository = context.getBean(CarsRepository.class);


    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringDataConfig.class);
    private UserDataRepository repository = context.getBean(UserDataRepository.class);

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String showSignIn() {
        return "signin";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST, params = {"!login", "!password"})
    @ResponseBody
    public String getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return "Current user : " + session.getAttribute("login");
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST, params = {"login", "password"})
    public String authenticate(@RequestParam("login") String login, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
//        Optional<User> current = repository.authenticate(login, password);
        Optional<User> current = repository.findByLoginAndPassword(login, password);
        if (current.isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            session.setAttribute("id", current.get().getId());
            return "item_list";
        } else {
            try {
                PrintWriter out = response.getWriter();
                out.println("Wrong credentials!");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "signin";
    }

    @RequestMapping(value = "/signout", method = RequestMethod.POST)
    public String signOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "signin";
    }

}
