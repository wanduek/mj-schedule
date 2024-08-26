package com.sparta.scheduleserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sparta.scheduleserver.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
