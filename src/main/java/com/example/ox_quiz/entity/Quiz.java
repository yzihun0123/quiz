package com.example.ox_quiz.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Quiz extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean answer;

    @Column(nullable = false)
    private String writer;
}
