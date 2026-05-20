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

    public void quizRandom(QuizDto quizDto) {
        Quiz quiz = (Quiz) quizRepository
                .findAll()
                .stream()
                .map(x->QuizDto.toQuizDto(x))
                .toList();
        Random random = new Random();
    }
}
