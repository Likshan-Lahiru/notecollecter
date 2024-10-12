package lk.ijse.gdse.aad67.notecollecter.service;



import lk.ijse.gdse.aad67.notecollecter.dto.impl.UserDTO;
import lk.ijse.gdse.aad67.notecollecter.dto.impl.UserStatus;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserStatus getUser(String userId);
    void deleteUser(String userId);
    void updateUser(String userId, UserDTO userDTO);
}
