package com.example.ox_quiz.repository;

import com.example.ox_quiz.entity.Member;
import com.example.ox_quiz.entity.RoleType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test()
    @DisplayName("첫 테스트")
    void contextLoads() {
        System.out.println("첫 테스트입니다.");
    }

    @Test
    @DisplayName("어드민 만들고 확인")
    void createAdmin() {
        Member member = new Member();
        member.setId("admin01");
        member.setPassword(
                bCryptPasswordEncoder.encode("1111"));
        member.setRole(RoleType.ADMIN);
        memberRepository.save(member);
        System.out.println("관리자 생성 완료");
    }
}
