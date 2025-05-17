package com.ming.mingchat.controller;

import com.ming.mingchat.dto.MemberSignUpRequest;
import com.ming.mingchat.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Validated @RequestBody MemberSignUpRequest request) {
        memberService.signUp(request);
        return ResponseEntity.status(201).body("회원가입 성공!");
    }


}
