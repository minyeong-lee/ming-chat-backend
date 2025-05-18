package com.ming.mingchat.controller;

import com.ming.mingchat.dto.request.MemberLoginRequest;
import com.ming.mingchat.dto.request.MemberSignUpRequest;
import com.ming.mingchat.dto.response.MemberLoginResponse;
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

    /* 회원가입 */
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Validated @RequestBody MemberSignUpRequest request) {
        memberService.signUp(request);
        return ResponseEntity.status(201).body("회원가입 성공!");
    }

    /* 로그인 */
    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponse> login(@Validated @RequestBody MemberLoginRequest request) {
        MemberLoginResponse response = memberService.login(request);
        return ResponseEntity.ok(response);
    }
}
