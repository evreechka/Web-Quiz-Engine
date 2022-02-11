package com.mari.engine.repos;

import com.mari.engine.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findUserById(Long id);
    User findUserByEmail(String email);
}
