package ru.tinkoff.notes.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "note")
public record NoteProperties(int maxLength) {
}
