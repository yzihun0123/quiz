package com.example.ox_quiz.service;

import com.example.ox_quiz.dto.MemberDto;
import com.example.ox_quiz.entity.Member;
import com.example.ox_quiz.entity.MemberStatus;
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
        member.setStatus(MemberStatus.PENDING);
        memberRepository.save(member);
    }

    // 비밀번호 업데이트
    public void updatePassword(MemberDto dto) {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(()-> new RuntimeException("회원 없음"));
        member.setPassword(
                passwordEncoder.encode(dto.getMemberPassword())
        );
        memberRepository.save(member);
    }

    // 상태 업데이트
    public void updateStatus(Long memberNo) {
        Member member = memberRepository.findById(memberNo)
                .orElseThrow(()-> new RuntimeException("회원 없음"));
        member.setStatus(MemberStatus.APPROVED);
        memberRepository.save(member);
    }

    // admin에서 비밀번호 업데이트
    public void adminUpdatePassword(Long memberNo, String newPassword) {
        Member member = memberRepository.findById(memberNo)
                .orElseThrow(() -> new RuntimeException("회원 없음"));
        member.setPassword(passwordEncoder.encode(newPassword));
        memberRepository.save(member);
    }

    // 전체 맴버 조회
    public List<MemberDto> findAll() {
        return memberRepository.findAll()
                .stream()
                .map(x -> MemberDto.toMemberDto(x))
                .toList();
    }

    // ID 조회
    public MemberDto findByMemberId(String id) {
        Member member = memberRepository
                .findById(id)
                .orElse(null);
        if (member == null) {
            return null;
        }
        return MemberDto.toMemberDto(member);
    }

    // 로그인
    public MemberDto login (MemberDto dto) {
        MemberDto loginDto = findByMemberId(dto.getMemberId());
        if (loginDto == null) return null;
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
