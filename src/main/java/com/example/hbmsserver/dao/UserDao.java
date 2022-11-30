package com.example.hbmsserver.dao;

import com.example.hbmsserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    Optional<User> findUserById(Long id);
}
