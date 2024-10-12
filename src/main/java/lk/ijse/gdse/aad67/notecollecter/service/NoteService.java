package lk.ijse.gdse.aad67.notecollecter.service;


import lk.ijse.gdse.aad67.notecollecter.dto.impl.NoteDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NoteService {
    ResponseEntity<Void> saveNote( NoteDTO noteDTO);
    List<NoteDTO> getAllNotes();
    NoteDTO getNote(String noteId);
    void deleteNote(String noteId);
    void updateNote(String noteId, NoteDTO noteDTO);
}
