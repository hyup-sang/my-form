package com.forms.api.member.service;

import com.forms.api.member.domain.Member;
import com.forms.api.member.domain.repository.MemberRepository;
import com.forms.api.member.dto.request.CreateMemberRequest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void createMember(CreateMemberRequest createMemberRequest) {
        Member member = new Member(
            createMemberRequest.getEmail(),
            createMemberRequest.getPassword(),
            createMemberRequest.getNickname()
        );

        this.memberRepository.save(member);
    }
}
