package com.forms.api.auth.infrastructure;

import com.forms.api.member.domain.Member;
import com.forms.api.member.domain.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
@Aspect
public class CurrentMemberAspect {
    MemberRepository memberRepository;

    public CurrentMemberAspect(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Around("execution(* *(.., @CurrentMember (*), ..))")
    public Object currentMember (ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String email = request.getUserPrincipal().getName();
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
            new UsernameNotFoundException("사용자를 찾을 수 없습니다. email: " + email)
        );
        Object[] args = { member, joinPoint.getArgs()[1] };
        return joinPoint.proceed(args);
    }
}
