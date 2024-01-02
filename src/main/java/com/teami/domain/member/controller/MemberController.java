package com.teami.domain.member.controller;

import com.teami.domain.member.dto.request.LoginRequest;
import com.teami.domain.member.dto.request.MemberRequest;
import com.teami.domain.member.dto.request.VisitorCommentReq;
import com.teami.domain.member.entitty.Member;
import com.teami.domain.member.service.MemberService;
import com.teami.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
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

    @PostMapping("/login")
    public ApiResponse<Member> login(@RequestBody LoginRequest loginRequest){
        Member res = memberService.login(loginRequest);
        return ApiResponse.onSuccess(res);
    }


    @Operation(summary = "방명록 생성 API")
    @PostMapping("/visitorComment")
    public ApiResponse<Boolean> addVisitorComment(@RequestBody VisitorCommentReq visitorCommentReq){
        memberService.addVisitorComment(visitorCommentReq);
        return ApiResponse.onSuccess(null);
    }

}
