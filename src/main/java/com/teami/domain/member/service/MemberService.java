package com.teami.domain.member.service;


import com.teami.domain.member.entitty.Member;
import com.teami.domain.member.entitty.MemberRepository;
import com.teami.global.apiPayload.ExceptionHandler;
import com.teami.global.apiPayload.code.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findById(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isEmpty()) {
            throw new ExceptionHandler(ErrorStatus.MEMBER_NOT_FOUND);
        }
        return member.get();
    }
}