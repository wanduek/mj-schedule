package com.sparta.scheduleserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public User(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.updatedDate = LocalDateTime.now();
    }
}
