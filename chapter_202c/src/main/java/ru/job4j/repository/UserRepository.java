package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.User;

public interface UserRepository extends CrudRepository<User, String> {
}
