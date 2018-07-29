package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.User;

import java.util.Optional;

/**
 * Spring data repository for car shop web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface UserDataRepository extends CrudRepository<User, Integer> {

    Optional<User> findByLoginAndPassword(String login, String password);
}
