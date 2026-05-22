package com.example.ox_quiz.dto;

import com.example.ox_quiz.entity.Member;
import com.example.ox_quiz.entity.MemberStatus;
import com.example.ox_quiz.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long memberNo;
    private String memberId;
    private String memberPassword;
    private RoleType role;
    // 생성일
    private LocalDateTime createdAt;
    // 수정일
    private LocalDateTime updatedAt;
    private MemberStatus status;
    private Integer memberAnswerTrue;
    private Integer memberAnswerFalse;

    // Dto -> Entity
    public static Member toEntity(MemberDto memberDto) {
        Member member = new Member();
        member.setNo(memberDto.getMemberNo());
        member.setId(memberDto.getMemberId());
        member.setPassword(memberDto.getMemberPassword());
        member.setRole(memberDto.getRole());
        member.setStatus(memberDto.getStatus());
        member.setAnswerTrue(memberDto.getMemberAnswerTrue());
        member.setAnswerFalse(memberDto.getMemberAnswerFalse());
        // 생성일과 수정일은 Entity에 전달할 이유가 없음.
        return member;
    }
    // Entity -> Dto
    public static MemberDto toMemberDto(Member member) {
        return new MemberDto(
                member.getNo(),
                member.getId(),
                member.getPassword(),
                member.getRole(),
                member.getCreatedAt(),
                member.getUpdatedAt(),
                member.getStatus(),
                member.getAnswerTrue(),
                member.getAnswerFalse()
        );
    }
}
