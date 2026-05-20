package com.example.ox_quiz.dto;

import com.example.ox_quiz.entity.Quiz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private Long quizId;
    private String quizContent;
    private Boolean quizAnswer;
    private String quizWriter;
    // 생성일
    private LocalDateTime createdAt;
    // 수정일
    private LocalDateTime updatedAt;

    public QuizDto(Long id, String content, boolean answer, String writer) {
        this.quizId = id;
        this.quizContent = content;
        this.quizAnswer = answer;
        this.quizWriter = writer;
    }

    // Dto -> Entity
    public static Quiz toEntity(QuizDto quizDto) {
        Quiz quiz = new Quiz();
        quiz.setId(quizDto.getQuizId());
        quiz.setContent(quizDto.getQuizContent());
        quiz.setAnswer(quizDto.getQuizAnswer());
        quiz.setWriter(quizDto.getQuizWriter());
        return quiz;
    }

    // Entity -> Dto
    public static QuizDto toQuizDto(Quiz quiz) {
        return new QuizDto(
                quiz.getId(),
                quiz.getContent(),
                quiz.isAnswer(),
                quiz.getWriter()
        );
    }
}
