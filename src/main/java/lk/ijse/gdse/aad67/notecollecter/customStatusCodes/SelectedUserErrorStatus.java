package lk.ijse.gdse.aad67.notecollecter.customStatusCodes;


import lk.ijse.gdse.aad67.notecollecter.dto.impl.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedUserErrorStatus implements UserStatus {
    private int status;
    private String StatusMessage;
}
