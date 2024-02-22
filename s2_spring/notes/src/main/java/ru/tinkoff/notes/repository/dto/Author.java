package ru.tinkoff.notes.repository.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;

public record Author(@Id String name, String email, String password) implements Persistable<String> {
    @Override
    public String getId() {
        return name;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
