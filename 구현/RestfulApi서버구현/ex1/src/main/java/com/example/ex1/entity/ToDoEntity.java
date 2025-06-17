package com.example.ex1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String writer;

    private LocalDateTime dueDate;

    @Builder
    protected ToDoEntity(Long id, String title, String writer, LocalDateTime dueDate) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.dueDate = dueDate;
    }
}
