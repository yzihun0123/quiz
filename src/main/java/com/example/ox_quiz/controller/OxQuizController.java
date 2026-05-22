package com.example.ox_quiz.controller;

import com.example.ox_quiz.dto.MemberDto;
import com.example.ox_quiz.dto.QuizDto;
import com.example.ox_quiz.repository.QuizRepository;
import com.example.ox_quiz.service.MemberService;
import com.example.ox_quiz.service.QuizService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.security.web.util.RedirectUrlBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
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
        return "index";
    }

    @PostMapping("/member/join")
    public String joinMember(@ModelAttribute("dto") MemberDto dto,
                             RedirectAttributes redirectAttributes) {
        log.info("result : " + dto);
        memberService.signup(dto);
        redirectAttributes.addFlashAttribute("message",
                "회원가입 완료");
        return "redirect:/";
    }

    @GetMapping("/member/login")
    public String login() {
        return "member/login";
    }

    @PostMapping("/member/login")
    public String loginMember(MemberDto dto, HttpSession session,
                              RedirectAttributes redirectAttributes) {
        MemberDto loginDto = memberService.login(dto);
        if (loginDto == null) {
            redirectAttributes.addFlashAttribute("loginFail",
                    "아이디 또는 비밀번호가 틀렸습니다.");
            return "redirect:/member/login";
        } else {
            session.setAttribute("loginDto", loginDto);
            session.setAttribute("loginId", loginDto.getMemberId());
            session.setAttribute("role", loginDto.getRole());
            session.setAttribute("status", loginDto.getStatus());
            session.setAttribute("memberAnswerTrue",
                    loginDto.getMemberAnswerTrue());
            session.setAttribute("memberAnswerFalse",
                    loginDto.getMemberAnswerFalse());
            session.setAttribute("joinDate", loginDto.getCreatedAt());
            session.setMaxInactiveInterval(1800);
            return "redirect:/member/my-page";
        }
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @GetMapping("/member/my-page")
    public String myPage() {
        return "member/my-page";
    }

    @PostMapping("/member/password")
    public String updatePassword(@ModelAttribute("dto") MemberDto dto,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        String myNo = (String) session.getAttribute("loginId");
        MemberDto searchMember = memberService.findByMemberId(myNo);
            searchMember.setMemberPassword(dto.getMemberPassword());
            memberService.updatePassword(searchMember);
            redirectAttributes.addFlashAttribute("message"
                    , "비밀번호가 수정되었습니다");

        return "redirect:/member/my-page";
    }

    @GetMapping("/admin/members")
    public String memberList(Model model) {
        List<MemberDto> memberDtoList = memberService.findAll();
        if (ObjectUtils.isEmpty(memberDtoList)) {
        } else {
            model.addAttribute("memberList", memberDtoList);
        }
        return "admin/member-list";
    }

    @PostMapping("/admin/member/approve")
    public String memberApprove(@RequestParam("memberNo") Long memberNo){
        memberService.updateStatus(memberNo);
        return "redirect:/admin/members";
    }

    @PostMapping("/admin/member/password")
    public String memberPasswordEdit(@RequestParam("memberNo") Long memberNo,
                                     @RequestParam("newPassword") String newPassword) {
        memberService.adminUpdatePassword(memberNo, newPassword);
        return "redirect:/admin/members";
    }

    @GetMapping("/quiz")
    public String quizList(Model model) {
        List<QuizDto> quizDtoList = quizService.findAllQuiz();
        model.addAttribute("quizList", quizDtoList);
        return "quiz/list";
    }

    @PostMapping("/quiz/insert")
    public String quizInsert(@ModelAttribute QuizDto quizDto) {
        quizService.InsertQuiz(quizDto);
        return "redirect:/quiz";
    }

    @GetMapping("quiz/{id}")
    public String update(@PathVariable Long id, Model model) {
        QuizDto quizDto = quizService.findQuizById(id);
        model.addAttribute("quiz", quizDto);
        return "quiz/update";
    }

    @PostMapping("/quiz/update")
    public String quizUpdate(@ModelAttribute QuizDto quizDto) {
        quizService.updateQuiz(quizDto);
        return "redirect:/quiz";
    }

    @PostMapping("/quiz/delete")
    public String result(@ModelAttribute QuizDto quizDto) {
        quizService.deleteQuiz(quizDto);
        return "redirect:/quiz";
    }

    @GetMapping("/quiz/play")
    public String play(Model model) {
        List<QuizDto> quizDtoList = quizService.findAllQuiz();
        model.addAttribute("quizList", quizDtoList);
        return "quiz/play";
    }

}
