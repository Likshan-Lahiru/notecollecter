package lk.ijse.gdse.aad67.notecollecter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import lk.ijse.gdse.aad67.notecollecter.entity.impl.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity,String> {
}
