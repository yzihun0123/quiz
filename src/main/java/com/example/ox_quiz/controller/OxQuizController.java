package com.example.ox_quiz.controller;

import com.example.ox_quiz.dto.MemberDto;
import com.example.ox_quiz.dto.QuizDto;
import com.example.ox_quiz.repository.QuizRepository;
import com.example.ox_quiz.service.MemberService;
import com.example.ox_quiz.service.QuizService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping()
@RequiredArgsConstructor
@Slf4j
public class OxQuizController {
    private final MemberService memberService;
    private final QuizService quizService;

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/member/join")
    public String join() {
        return "member/join";
    }

    @PostMapping("/member/join")
    public String joinMember(@ModelAttribute("dto") MemberDto dto,
                             RedirectAttributes redirectAttributes) {
        log.info("result : " + dto);
        memberService.signup(dto);
        redirectAttributes.addFlashAttribute("message",
                "회원가입 완료");
        return "index";
    }

    @GetMapping("/quiz")
    public String quizList(Model model) {
        List<QuizDto> quizDtoList = quizService.findAllQuiz();
        if (ObjectUtils.isEmpty(quizDtoList)) {
        } else {
            model.addAttribute("quizList", quizDtoList);
        }
        return "quiz/list";
    }

    @GetMapping("/member/login")
    public String login() {
        return "member/login";
    }

    @PostMapping("/member/login")
    public String loginMember(MemberDto dto, HttpSession session){
        MemberDto loginDto = memberService.login(dto);
        if (loginDto == null) {
            return "redirect:/member/login";
        } else
        {
            session.setAttribute("loginDto", loginDto);
            session.setMaxInactiveInterval(1800);
            return "redirect:/member/my-page";
        }
    }

    @GetMapping("/member/memberList")
    public String memberList(Model model) {
        List<MemberDto> memberDtoList = memberService.findAll();
        if (ObjectUtils.isEmpty(memberDtoList)) {
        } else {
            model.addAttribute("memberList", memberDtoList);
        }
        return "admin/member-list";
    }

    @GetMapping("/member/my-page")
    public String myPage() {
        return "member/my-page";
    }

    @GetMapping("/quiz/play")
    public String play() {
        return "quiz/play";
    }

    @GetMapping("/quiz/result")
    public String result() {
        return "quiz/result";
    }

    @GetMapping("/quiz/update")
    public String update() {
        return "quiz/update";
    }

    @PostMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @PostMapping("/admin/member/password")
    public String memberPasswordEdit() {
        return "admin/member-list";
    }
}
