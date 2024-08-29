package com.sparta.scheduleserver.model.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sparta.scheduleserver.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
