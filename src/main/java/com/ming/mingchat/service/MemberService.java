package com.ming.mingchat.service;

import com.ming.mingchat.domain.Member;
import com.ming.mingchat.dto.MemberSignUpRequest;
import com.ming.mingchat.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void signUp(MemberSignUpRequest request) {
        Member member = new Member(
                request.getEmail(),
                request.getPassword(),
                request.getNickname()
        );
        memberRepository.save(member);
    }
}
