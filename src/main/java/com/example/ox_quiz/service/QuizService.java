package com.example.ox_quiz.service;

import com.example.ox_quiz.dto.QuizDto;
import com.example.ox_quiz.entity.Quiz;
import com.example.ox_quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;


    public List<QuizDto> findAllQuiz() {
        return quizRepository
                .findAll()
                .stream()
                .map(x->QuizDto.toQuizDto(x))
                .toList();
    }

    public void InsertQuiz(QuizDto dto) {
        Quiz quiz = new Quiz();
        quiz.setContent(dto.getQuizContent());
        quiz.setAnswer(dto.getQuizAnswer());
        quiz.setWriter(dto.getQuizWriter());
        quizRepository.save(quiz);
    }

    // QuizService에 추가
    public QuizDto findQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("퀴즈 없음"));
        return QuizDto.toQuizDto(quiz);
    }

    public void updateQuiz(QuizDto quizDto) {
        Quiz quiz = QuizDto.toEntity(quizDto);
        quizRepository.save(quiz);
    }

    public void deleteQuiz(QuizDto quizDto) {
        Quiz quiz = new Quiz();
        quiz.setId(quizDto.getQuizId());
        quizRepository.delete(quiz);
    }
}
