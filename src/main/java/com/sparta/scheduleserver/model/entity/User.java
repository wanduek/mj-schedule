package com.sparta.scheduleserver.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Schedule> createSchedule = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<UserSchedule> userSchedules = new ArrayList<>();

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public User(String username ,String password,  String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    // 유저 정보 수정
    public void update(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.updatedDate = LocalDateTime.now();
    }

    public void setPassword(String encodedPassword) {
    }
}
