package com.teami.domain.member.controller;

import com.teami.domain.member.dto.MemberRequest;
import com.teami.domain.member.service.MemberService;
import com.teami.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private MemberService memberService;


    @PostMapping("/signup")
    public ApiResponse<MemberRequest> addMember(@RequestBody MemberRequest memberRequest){
        boolean res = memberService.addMember(memberRequest);
        return ApiResponse.onSuccess(null);
    }

}
