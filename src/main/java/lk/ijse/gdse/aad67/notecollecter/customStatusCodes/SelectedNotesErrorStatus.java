package lk.ijse.gdse.aad67.notecollecter.customStatusCodes;


import lk.ijse.gdse.aad67.notecollecter.dto.impl.NoteStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedNotesErrorStatus implements NoteStatus {

    private int errorCode;
    private String errorMessage;

}
