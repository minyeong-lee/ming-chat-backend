package com.ming.mingchat.service;

import com.ming.mingchat.domain.Member;
import com.ming.mingchat.dto.request.MemberLoginRequest;
import com.ming.mingchat.dto.request.MemberSignUpRequest;
import com.ming.mingchat.dto.response.MemberLoginResponse;
import com.ming.mingchat.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /* 회원가입 */
    @Transactional
    public void signUp(MemberSignUpRequest request) {
        Member member = new Member(
                request.getEmail(),
                request.getPassword(),
                request.getNickname()
        );
        memberRepository.save(member);
    }

    /* 로그인 */
    @Transactional
    public MemberLoginResponse login(MemberLoginRequest request) {
        //입력받은 이메일과 비밀번호가 맞는지 검증하기
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if (!member.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return new MemberLoginResponse(member.getEmail(), member.getNickname());
    }
}
