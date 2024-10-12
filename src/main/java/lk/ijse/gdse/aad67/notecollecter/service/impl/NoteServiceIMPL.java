package lk.ijse.gdse.aad67.notecollecter.service.impl;

import lk.ijse.gdse.aad67.notecollecter.dao.NoteDao;
import lk.ijse.gdse.aad67.notecollecter.dto.impl.NoteDTO;
import lk.ijse.gdse.aad67.notecollecter.entity.impl.NoteEntity;
import lk.ijse.gdse.aad67.notecollecter.exception.NoteNotFoundException;
import lk.ijse.gdse.aad67.notecollecter.service.NoteService;
import lk.ijse.gdse.aad67.notecollecter.util.AppUtil;
import lk.ijse.gdse.aad67.notecollecter.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoteServiceIMPL implements NoteService {

    @Autowired
    private NoteDao noteDao;

    @Autowired
    private Mapping modelMapper;

    @Override
    public ResponseEntity<Void> saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.generateNoteId());

        noteDao.save(modelMapper.toNoteEntity(noteDTO));

        return null;
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        List<NoteEntity> all = noteDao.findAll();
        return modelMapper.asNoteDTOList(all);
    }

    @Override
    public NoteDTO getNote(String noteId) {
        if(noteDao.existsById(noteId)){
            return modelMapper.toNoteDTO(noteDao.findById(noteId).get());
        }else {
            return null;
        }
    }

    @Override
    public void deleteNote(String noteId) {
        Optional<NoteEntity> existedNote = noteDao.findById(noteId);
        if(!existedNote.isPresent()){
            throw new NoteNotFoundException("User with id " + noteId + " not found");
        }else {

            noteDao.deleteById(noteId);


        }
    }

    @Override
    public void updateNote(String noteId, NoteDTO noteDTO) {
        Optional<NoteEntity> tmpNote = noteDao.findById(noteId);
        if(tmpNote.isPresent()) {
            tmpNote.get().setCreatedDate(noteDTO.getCreatedDate());
            tmpNote.get().setNoteDesc(noteDTO.getNoteDesc());
            tmpNote.get().setNoteTitle(noteDTO.getNoteTitle());
            tmpNote.get().setPriorityLevel(noteDTO.getPriorityLevel());

        }
    }
}
