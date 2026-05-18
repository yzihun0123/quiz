package com.example.ox_quiz.repository;

import com.example.ox_quiz.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
