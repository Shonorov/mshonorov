package ru.job4j.users;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ValidateServiceTest {

    @Test
    public void whenFindAllThenNotEmpty() {
        ValidateService service = new ValidateService();
//        service.findAll();
//        System.out.println(service.getCityID("Moscow"));
//        System.out.println(service.getCityName("0"));
        for (User user : service.findAll()) {
            System.out.println(user);
        }
    }

    @Test
    public void whenFindByIDThenNotEmpty() {
        ValidateService service = new ValidateService();
//        service.findAll();
//        System.out.println(service.getCityID("Moscow"));
//        System.out.println(service.getCityName("0"));
//        for (User user : service.findAll()) {
//            System.out.println(user);
//        }
    }

    @Test
    public void whenAddThenPresent() {
        ValidateService service = new ValidateService();
        User user1 = new User("test1", "user1", "user1", "user1@contoso.com", LocalDateTime.now(), "user1", "user", "Ukraine", "Kiev");
        service.add(user1);

    }
}
