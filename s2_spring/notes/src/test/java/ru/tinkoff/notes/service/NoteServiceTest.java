package ru.tinkoff.notes.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.tinkoff.notes.repository.NoteRepository;
import ru.tinkoff.notes.repository.dto.Note;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class NoteServiceTest {

    @MockBean
    NoteRepository noteRepository;

    @Autowired
    NoteService noteService;

    @Test
    void testCreateNote() {
        Mockito.when(noteRepository.save(any()))
                .then((Answer<Note>) invocationOnMock -> {
                    Note argument = invocationOnMock.getArgument(0);
                    Note result = new Note(1L, argument.author(), argument.text(), argument.mentions());
                    return result;
                });

        Note note = noteService.createNote("testAuthor", "I mention @testUser");

        Assertions.assertThat(note.mentions()).containsExactly("testUser");
    }

}