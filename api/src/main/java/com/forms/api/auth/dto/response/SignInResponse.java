package com.forms.api.auth.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignInResponse {

    private final Long memberId;

    private final String nickname;

    private final String email;

    private final String token;

}
