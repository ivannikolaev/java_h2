package ru.tinkoff.notes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.notes.aop.ExecutionTime;
import ru.tinkoff.notes.repository.dto.Note;
import ru.tinkoff.notes.repository.NoteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final TextParser textParser;

    public List<Note> getAuthorNotes(String author) {
        return noteRepository.findAllByAuthor(author);
    }

    @ExecutionTime
    public Note createNote(String author, String text) {
        return noteRepository.save(new Note(null, author, text, textParser.parse(text)));
    }

    public List<Note> getAuthorMentions(String author) {
        return noteRepository.findMentions(author);
    }
}
