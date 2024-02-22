package ru.tinkoff.notes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.stereotype.Service;
import ru.tinkoff.notes.repository.AuthorRepository;
import ru.tinkoff.notes.repository.dto.Author;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Optional<Author> getAuthor(String name) {
        return authorRepository.findById(name);
    }

    public boolean createAuthor(String name, String email, String password) {
        try {
            authorRepository.save(new Author(name, email, password));
            return true;
        } catch (DbActionExecutionException e) {
            return false;
        }
    }
}
