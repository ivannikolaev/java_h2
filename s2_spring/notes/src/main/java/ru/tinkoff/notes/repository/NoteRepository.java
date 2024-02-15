package ru.tinkoff.notes.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.notes.repository.dto.Note;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findAllByAuthor(String author);

    @Query("SELECT id, author, text, mentions FROM note WHERE ARRAY_CONTAINS(mentions, :name)")
    List<Note> findMentions(String name);
}
