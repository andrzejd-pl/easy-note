package pl.andrzejd.easynotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.andrzejd.easynotes.exception.ResourceNotFoundException;
import pl.andrzejd.easynotes.model.Note;
import pl.andrzejd.easynotes.repository.NoteRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = {"https://s2.andrzejd.pl", "http://s2.andrzejd.pl"}, maxAge = 3600)
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }

    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId).
                orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") Long noteId, @Valid @RequestBody Note noteDetails) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        return noteRepository.save(note);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}
