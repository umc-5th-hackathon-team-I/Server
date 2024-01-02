package com.teami.domain.member.controller;

import com.teami.domain.member.dto.request.LoginRequest;
import com.teami.domain.member.dto.request.MemberRequest;
import com.teami.domain.member.dto.request.VisitorCommentReq;
import com.teami.domain.member.dto.response.MemberIdResponse;
import com.teami.domain.member.dto.response.VisitorCommentRes;
import com.teami.domain.member.entitty.Member;
import com.teami.domain.member.service.MemberService;
import com.teami.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private MemberService memberService;


    @PostMapping("/signup")
    public ApiResponse<MemberIdResponse> addMember(@RequestBody MemberRequest memberRequest){
        boolean res = memberService.addMember(memberRequest);
        return ApiResponse.onSuccess(null);
    }

    @PostMapping("/login")
    public ApiResponse<MemberIdResponse> login(@RequestBody LoginRequest loginRequest){
        Member res = memberService.login(loginRequest);
        return ApiResponse.onSuccess(new MemberIdResponse(res.getId()));
    }


    @Operation(summary = "방명록 생성 API",description = "사용자가 작성한 리뷰의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @PostMapping("/visitorComment")
    @Parameters({
            @Parameter(name = "ownerId", description = "방문 당하는 사용자의 id입니다"),
            @Parameter(name = "writerId", description = "편지 작성자 아이디 입니다. String(login_id아님)"),
    })

    public ApiResponse<Boolean> addVisitorComment(@RequestBody VisitorCommentReq visitorCommentReq){
        memberService.addVisitorComment(visitorCommentReq);
        return ApiResponse.onSuccess(null);
    }


    @Operation(summary = "방명록 리스트 조회 API",description = "사용자가 받은 편지의 목록을 조회하는 API이다")
    @GetMapping("/visitorComment")
    @Parameters({
            @Parameter(name = "memberId", description = "사용자 아이디 입니다."),
    })
    public ApiResponse<List<VisitorCommentRes>> getVisitCommentList(@RequestParam Long memberId){
        List<VisitorCommentRes> commentList = memberService.getVisitCommentList(memberId);
        return ApiResponse.onSuccess(commentList);
    }

    @Operation(summary = "방명록 상세 조회 API",description = "사용자가 받은 편지 상세 조회하는 API이다")
    @GetMapping("/visitorCommentDetail")
    @Parameters({
            @Parameter(name = "memberId", description = "사용자 아이디 입니다."),
            @Parameter(name = "calendarVisitorId", description = "편지 id입니다. 리스트로 보여줄 때 편지 id를 같이 전달 되게 했으니 클릭시 아이디를 넘겨주면 됩니다.")

    })
    public ApiResponse<VisitorCommentRes> getVisitComment(@RequestParam Long memberId, @RequestParam Long calendarVisitorId){
        VisitorCommentRes comment = memberService.getVisitComment(memberId,calendarVisitorId);
        return ApiResponse.onSuccess(comment);
    }
}
