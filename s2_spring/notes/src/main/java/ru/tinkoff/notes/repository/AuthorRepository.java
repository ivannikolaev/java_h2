package ru.tinkoff.notes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.notes.repository.dto.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, String> {
}
