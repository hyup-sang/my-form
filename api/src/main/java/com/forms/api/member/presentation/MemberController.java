package com.forms.api.member.presentation;

import com.forms.api.member.domain.Member;
import com.forms.api.member.dto.request.CreateMemberRequest;
import com.forms.api.member.dto.request.ModifyMemberRequest;
import com.forms.api.member.service.MemberService;
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

    @GetMapping("/{memberId}")
    public Member getMember(@PathVariable Long memberId) {
        return memberService.getMember(memberId);
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<Void> modifyMember(
        @PathVariable Long memberId,
        @Valid @RequestBody ModifyMemberRequest modifyMemberRequest
        ) {
        memberService.modifyMember(memberId, modifyMemberRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }
}
