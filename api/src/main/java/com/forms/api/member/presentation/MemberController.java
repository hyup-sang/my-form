package com.forms.api.member.presentation;

import com.forms.api.member.dto.request.CreateMemberRequest;
import com.forms.api.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping()
    public ResponseEntity<Void> createMember(@Valid @RequestBody CreateMemberRequest createMemberRequest) {
        memberService.createMember(createMemberRequest);
        return ResponseEntity.created(URI.create("/member")).build();
    }
}
