package lk.ijse.gdse.aad67.notecollecter.service.impl;

import jakarta.transaction.Transactional;

import lk.ijse.gdse.aad67.notecollecter.customStatusCodes.SelectedUserErrorStatus;
import lk.ijse.gdse.aad67.notecollecter.dao.UserDao;

import lk.ijse.gdse.aad67.notecollecter.dto.impl.UserDTO;
import lk.ijse.gdse.aad67.notecollecter.dto.impl.UserStatus;
import lk.ijse.gdse.aad67.notecollecter.entity.impl.UserEntity;
import lk.ijse.gdse.aad67.notecollecter.exception.DataPersistException;
import lk.ijse.gdse.aad67.notecollecter.exception.UserNotFoundException;
import lk.ijse.gdse.aad67.notecollecter.service.UserService;
import lk.ijse.gdse.aad67.notecollecter.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveUser(UserDTO userDTO) {
        UserEntity savedUser =
                userDao.save(mapping.toUserEntity(userDTO));
        if (savedUser == null) {
            throw new DataPersistException();
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> allUsers = userDao.findAll();
        return mapping.asUserDTOList(allUsers);
    }

    @Override
    public UserStatus getUser(String userId) {


        if (userDao.existsById(userId)) {
            UserEntity selectedUser = userDao.getReferenceById(userId);
            return mapping.toUserDTO(selectedUser);
        }else {
           return new SelectedUserErrorStatus(2,"user not found");
        }

    }

    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> existedUser = userDao.findById(userId);
        if(!existedUser.isPresent()){
            throw new UserNotFoundException("User with id " + userId + " not found");
        }else {

            userDao.deleteById(userId);


        }
    }

    @Override
    public void updateUser(String userId, UserDTO userDTO) {
        Optional<UserEntity> tmpUser = userDao.findById(userId);
        if(tmpUser.isPresent()) {
            tmpUser.get().setFirstName(userDTO.getFirstName());
            tmpUser.get().setLastName(userDTO.getLastName());
            tmpUser.get().setEmail(userDTO.getEmail());
            tmpUser.get().setPassword(userDTO.getPassword());
            tmpUser.get().setProfilePic(userDTO.getProfilePic());
        }
    }
}
