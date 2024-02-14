package com.forms.api.forms.presentation;

import com.forms.api.auth.infrastructure.CurrentMember;
import com.forms.api.forms.domain.Forms;
import com.forms.api.forms.dto.request.CreateFormsRequest;
import com.forms.api.forms.dto.request.ModifyFormsRequest;
import com.forms.api.forms.service.FormsService;
import com.forms.api.member.domain.Member;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

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

    @GetMapping("/{formsId}")
    public ResponseEntity<Forms> findForms(
        @PathVariable Long formsId
    ) {
        Forms forms = formsService.findForms(formsId);
        return ResponseEntity.ok(forms);
    }

    @PatchMapping("/{formsId}")
    public ResponseEntity<Void> modifyForms(
        @PathVariable Long formsId,
        @Valid @RequestBody ModifyFormsRequest modifyFormsRequest
    ) {
        formsService.modifyForms(formsId, modifyFormsRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{formsId}")
    public ResponseEntity<Void> deleteForms(
        @PathVariable Long formsId
    ) {
        formsService.deleteForms(formsId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<Forms>> findFormsList(
        @CurrentMember Member member
    ) {
        List<Forms> list = formsService.findFormsList(member);
        return ResponseEntity.ok(list);
    }

}
