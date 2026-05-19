package com.example.ox_quiz.controller;

import com.example.ox_quiz.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class OxQuizController {

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/join")
    public String joinMember(@ModelAttribute("dto") MemberDto dto) {
        return "index";
    }

    @GetMapping("/list")
    public String list() {
        return "list";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/memberList")
    public String memberList() {
        return "member-list";
    }

    @GetMapping("/myPage")
    public String myPage() {
        return "my-page";
    }

    @GetMapping("/play")
    public String play() {
        return "play";
    }

    @GetMapping("/result")
    public String result() {
        return "result";
    }

    @GetMapping("/update")
    public String update() {
        return "update";
    }

}
