package ru.tinkoff.notes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.notes.config.NoteProperties;
import ru.tinkoff.notes.controller.dto.CreateNoteRequest;
import ru.tinkoff.notes.repository.dto.Note;
import ru.tinkoff.notes.service.NoteService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "basic")
public class NoteController {

    private final NoteService noteService;
    private final NoteProperties noteProperties;
    private final Validator validator;

    @GetMapping("/notes")
    List<Note> myNotes(Principal principal) {
        return noteService.getAuthorNotes(principal.getName());
    }

    @PostMapping("/notes")
    @Operation(summary = "Create note")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created note",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateNoteRequest.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid note length",
                    content = @Content)})
    Note createNote(Principal principal, @Valid @RequestBody CreateNoteRequest createNoteRequest) {
//        Set<ConstraintViolation<CreateNoteRequest>> violations = validator.validate(createNoteRequest);
//        if (!violations.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, violations.toString());
//        }
//        if (createNoteRequest.text().length() > noteProperties.maxLength()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Text is too long");
//        }
        return noteService.createNote(principal.getName(), createNoteRequest.text());
    }

    @GetMapping("/notes/mentioned")
    List<Note> myMentions(Principal principal) {
        return noteService.getAuthorMentions(principal.getName());
    }

}
