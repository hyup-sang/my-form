package com.forms.api.auth.service;

import com.forms.api.auth.dto.request.SignInRequest;
import com.forms.api.member.domain.Member;
import com.forms.api.member.domain.repository.MemberRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;


@Service
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(
        MemberRepository memberRepository,
        JwtEncoder jwtEncoder,
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager
    ) {
        this.memberRepository = memberRepository;
        this.jwtEncoder = jwtEncoder;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public String signIn(SignInRequest signInRequest) throws BadRequestException {
        Member member = memberRepository
            .findByEmail(signInRequest.getEmail())
            .orElseThrow(() -> new BadRequestException("not exists"));

        if (!isMatchedPassword(signInRequest.getPassword(), member.getPassword())) {
            throw new BadRequestException("not matched password");
        }

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(
                signInRequest.getEmail(),
                signInRequest.getPassword()
            );

        Authentication authentication =
            authenticationManager.authenticate(authenticationToken);

        return generateToken(authentication);
    }

    private boolean isMatchedPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateToken(Authentication authentication) {
        var scope = authentication
            .getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(90, ChronoUnit.MINUTES))
            .subject(authentication.getName())
            .claim("scope", scope)
            .build();

        return this.jwtEncoder
            .encode(JwtEncoderParameters.from(claims))
            .getTokenValue();
    }
}
