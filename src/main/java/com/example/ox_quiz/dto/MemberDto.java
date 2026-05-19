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
    private MemberStatus memberStatus;
    private Integer memberAnswerTrue;
    private Integer memberAnswerFalse;

    // Dto -> Entity
    public static Member toEntity(MemberDto dto) {
        Member member = new Member();
        member.setNo(dto.getMemberNo());
        member.setId(dto.getMemberId());
        member.setPassword(dto.getMemberPassword());
        member.setRole(dto.getRole());
        member.setStatus(dto.getMemberStatus());
        member.setAnswerTrue(dto.getMemberAnswerTrue());
        member.setAnswerFalse(dto.getMemberAnswerFalse());
        // 생성일과 수정일은 Entity에 전달할 이유가 없음.
        return member;
    }
    // Entity -> Dto
    public static MemberDto toDto (Member member) {
        return new MemberDto(
                member.getNo(),
                member.getId(),
                member.getPassword(),
                member.getRole(),
                member.getUpdatedAt(),
                member.getCreatedAt(),
                member.getStatus(),
                member.getAnswerTrue(),
                member.getAnswerFalse()
        );
    }
}
