package com.forms.api.member.presentation;

import com.forms.api.member.dto.request.CreateMemberRequest;
import com.forms.api.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping()
    public void createMember(@Valid @RequestBody CreateMemberRequest createMemberRequest) {
        System.out.println(createMemberRequest.getEmail());
        System.out.println(createMemberRequest.getNickname());
        System.out.println(createMemberRequest.getPassword());
        this.memberService.createMember(createMemberRequest);

    }
}
