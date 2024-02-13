package com.forms.api.forms.presentation;

import com.forms.api.auth.infrastructure.CurrentMember;
import com.forms.api.forms.dto.request.CreateFormsRequest;
import com.forms.api.forms.service.FormsService;
import com.forms.api.member.domain.Member;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("forms")
public class FormsController {
    private final FormsService formsService;

    public FormsController(FormsService formsService) {
        this.formsService = formsService;
    }

    @PostMapping()
    public ResponseEntity<Void> createForms(
        @CurrentMember Member member,
        @Valid @RequestBody CreateFormsRequest createFormsRequest
    ) {
        formsService.createForms(member, createFormsRequest);

        return ResponseEntity.created(URI.create("/forms")).build();
    }


}
