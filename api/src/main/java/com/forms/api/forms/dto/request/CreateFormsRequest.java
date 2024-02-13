package com.forms.api.forms.dto.request;

import com.forms.api.forms.domain.FormsAccess;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateFormsRequest {
    @NotBlank
    private String title;

    private String description;

    private FormsAccess access;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

}
