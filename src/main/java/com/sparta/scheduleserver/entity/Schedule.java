package com.sparta.scheduleserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Entity
@Getter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "schedule", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>(); // comment를 List에 저장
    @OneToMany(mappedBy = "schedule", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<UserSchedule> userSchedules = new ArrayList<>();


    public Schedule(User author , String title, String content) {
        this.author = author;
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

    public void addUserSchedule(UserSchedule userSchedule) {
        this.userSchedules.add(userSchedule);
        userSchedule.setSchedule(this);
    }

    public void removeUserSchedule(UserSchedule userSchedule) {
        this.userSchedules.remove(userSchedule);
        userSchedule.setSchedule(null);
    }
}
