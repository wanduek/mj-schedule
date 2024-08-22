package com.sparta.scheduleserver.repository;

import com.sparta.scheduleserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByTitle(String title);
    Optional<User> findByContent(String content);

    Optional<User> findById(long id);
}
