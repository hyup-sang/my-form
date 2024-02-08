package com.forms.api.auth.service;

import com.forms.api.auth.dto.request.SignInRequest;
import com.forms.api.member.domain.Member;
import com.forms.api.member.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;

import static com.forms.api.auth.infrastructure.AuthUtil.passwordEncoder;

@Service
public class AuthService {
    MemberRepository memberRepository;

    public AuthService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void signIn(SignInRequest signInRequest) {
        Member member = memberRepository.findByEmail(signInRequest.getEmail());

        if (member == null) {
            System.out.println("not exists");
            return;
        }

        if (!isMatchedPassword(signInRequest.getPassword(), member.getPassword())) {
            System.out.println("not matched password");
            return;
        }

        System.out.println("success");
        return;
    }

    private boolean isMatchedPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder().matches(rawPassword, encodedPassword);
    }
}
