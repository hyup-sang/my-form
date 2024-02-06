package com.forms.api.member.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateMemberRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Length(min = 8, max = 30)
    private String password;

    @NotBlank
    @Length(max = 20)
    private String nickname;
}
