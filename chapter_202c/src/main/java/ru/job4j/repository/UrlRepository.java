package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.Url;

public interface UrlRepository extends CrudRepository<Url, String> {
}
