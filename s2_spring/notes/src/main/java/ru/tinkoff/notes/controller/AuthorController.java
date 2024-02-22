package ru.tinkoff.notes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.notes.api.AuthorApi;
import ru.tinkoff.notes.model.CreateAuthorRequest;
import ru.tinkoff.notes.model.GetAuthor200Response;
import ru.tinkoff.notes.service.AuthorService;

import static org.springframework.http.HttpStatus.CONFLICT;

@RestController
@RequiredArgsConstructor
public class AuthorController implements AuthorApi {
    private final AuthorService authorService;
    @Override
    public ResponseEntity<Void> createAuthor(CreateAuthorRequest createAuthorRequest) {
        if (authorService.createAuthor(createAuthorRequest.getName(), createAuthorRequest.getEmail(), createAuthorRequest.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(CONFLICT).build();
        }
    }

    @Override
    public ResponseEntity<GetAuthor200Response> getAuthor(String name) {
        return authorService.getAuthor(name)
                .map(author -> ResponseEntity.ok(new GetAuthor200Response(author.name(), author.email())))
                .orElse(ResponseEntity.notFound().build());
    }
}
