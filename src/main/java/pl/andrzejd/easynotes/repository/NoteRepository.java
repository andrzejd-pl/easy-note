package pl.andrzejd.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.andrzejd.easynotes.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}
