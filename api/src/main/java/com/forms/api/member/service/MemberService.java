package com.forms.api.member.service;

import com.forms.api.member.domain.Member;
import com.forms.api.member.domain.repository.MemberRepository;
import com.forms.api.member.dto.request.CreateMemberRequest;
import com.forms.api.member.dto.request.ModifyMemberRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(
        MemberRepository memberRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createMember(CreateMemberRequest createMemberRequest) {
        // Todo: 추후 에러 처리 수정
        if (memberRepository.existsByEmail(createMemberRequest.getEmail())) {
            throw new RuntimeException("중복된 이메일 입니다.");
        }

        if (memberRepository.existsByNickname(createMemberRequest.getNickname())) {
            throw new RuntimeException("중복된 닉네임 입니다.");
        }

        Member member = new Member(
            createMemberRequest.getEmail(),
            passwordEncoder.encode(createMemberRequest.getPassword()),
            createMemberRequest.getNickname()
        );

        this.memberRepository.save(member);
    }

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    public void modifyMember(Long memberId, ModifyMemberRequest modifyMemberRequest) {
        Member member = this.getMember(memberId);

        if (member == null) {
            return;
        }

        member.modifyMember(
            modifyMemberRequest.getPassword(),
            modifyMemberRequest.getNickname()
        );

        memberRepository.save(member);
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
