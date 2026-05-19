package com.example.ox_quiz.service;

import com.example.ox_quiz.dto.MemberDto;
import com.example.ox_quiz.entity.Member;
import com.example.ox_quiz.entity.RoleType;
import com.example.ox_quiz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // 회원가입
    public void signup(MemberDto dto) {
        Member member = new Member();
        member.setId(dto.getMemberId());
        member.setPassword(
                passwordEncoder.encode(dto.getMemberPassword())
        );
        member.setRole(RoleType.USER);
        memberRepository.save(member);
    }

    public List<MemberDto> findAll() {
        return memberRepository.findAll()
                .stream()
                .map(x -> MemberDto.toDto(x))
                .toList();
    }

    // ID 조회
    public MemberDto findById(String id) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) {
            return null;
        }
        return MemberDto.toDto(member);
    }

    // 로그인
    public MemberDto login (MemberDto dto) {
        MemberDto loginDto = findById(dto.getMemberId());
        if (loginDto == null) {
            return null;
        }
        boolean matches = passwordEncoder.matches(
                dto.getMemberPassword(),
                loginDto.getMemberPassword()
        );

        if (matches) {
            return loginDto;
        } else {
            return null;
        }
    }

    public boolean confirmPassword(String memberPassword, String strPassword) {
        return passwordEncoder.matches(strPassword, memberPassword);
    }
}
