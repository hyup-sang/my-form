package com.forms.api.forms.dto.request;

import com.forms.api.forms.domain.FormsAccess;
import com.forms.api.forms.domain.FormsStatus;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModifyFormsRequest {
    @Nullable
    private String title;

    @Nullable
    private String description;

    @Nullable
    private FormsAccess access;

    @Nullable
    private FormsStatus status;

    @Nullable
    private LocalDateTime startedAt;

    @Nullable
    private LocalDateTime endedAt;

}
