package com.teami.domain.member.service;

import com.teami.domain.member.entitty.Member;
import com.teami.domain.member.repository.MemberRepository;
import com.teami.global.apiPayload.ExceptionHandler;
import com.teami.global.apiPayload.code.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findMemberById(Long memberId) {
        Optional<Member> member =  memberRepository.findById(memberId);
        if(member.isEmpty()) throw new ExceptionHandler(ErrorStatus.MEMBER_NOT_FOUND);
        return member.get();
    }
}
