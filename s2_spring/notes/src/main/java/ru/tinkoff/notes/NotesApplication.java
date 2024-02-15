package ru.tinkoff.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.notes.config.NoteProperties;

@SpringBootApplication
@EnableConfigurationProperties(NoteProperties.class)
public class NotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotesApplication.class, args);
    }

}
