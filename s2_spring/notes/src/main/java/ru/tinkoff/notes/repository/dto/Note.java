package ru.tinkoff.notes.repository.dto;

import org.springframework.data.annotation.Id;

import java.util.List;

public record Note(@Id Long id, String author, String text, List<String> mentions) {
}
