package com.fatih.SpringSecurity.repositories;

import com.fatih.SpringSecurity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findByUuidCode(UUID uuidCode);

     User findByEmailAdress(String mail);


}
