package com.example.ox_quiz.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(unique = true, nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer answerTrue = 0;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer answerFalse = 0;

    // 회원 권한(RoleType)
    @Enumerated(EnumType.STRING)
    private RoleType role;

    // 승인 (MemberStatus)
    @Enumerated(EnumType.STRING)
    private MemberStatus status;
}
