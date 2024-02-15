package ru.tinkoff.notes.repository.dto;

import org.springframework.data.annotation.Id;

public record Author(@Id String name, String password) {
}
