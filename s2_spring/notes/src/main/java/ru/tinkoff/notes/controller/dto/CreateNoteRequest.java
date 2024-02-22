package ru.tinkoff.notes.controller.dto;

import jakarta.validation.constraints.Size;

public record CreateNoteRequest(@Size(min = 10, max = 100) String text) {
}
