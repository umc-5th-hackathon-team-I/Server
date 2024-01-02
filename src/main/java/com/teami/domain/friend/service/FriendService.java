package com.teami.domain.friend.service;

import com.teami.domain.friend.entity.Friend;
import com.teami.domain.friend.entity.FriendRepository;
import com.teami.domain.member.entitty.Member;
import com.teami.domain.member.service.MemberService;
import com.teami.global.apiPayload.ExceptionHandler;
import com.teami.global.apiPayload.code.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final MemberService memberService;

    @Transactional
    public Long createFriend(Long member1Id, Long member2Id) {
        validateSameMember(member1Id, member2Id);
        validateAlreadyFriend(member1Id, member2Id);

        Member member1 = memberService.findById(member1Id);
        Member member2 = memberService.findById(member2Id);

        return friendRepository.save(Friend.createFriend(member1, member2)).getId();
    }

    private void validateSameMember(Long member1Id, Long member2Id) {
        if (member1Id.equals(member2Id)) {
            throw new ExceptionHandler(ErrorStatus.SELF_FRIEND_REQUEST_NOT_ALLOWED);
        }
    }

    private void validateAlreadyFriend(Long member1Id, Long member2Id) {
        if (friendRepository.existsByMember1_IdAndAndMember2_Id(member1Id, member2Id) ||
                friendRepository.existsByMember1_IdAndAndMember2_Id(member2Id, member1Id)) {
            throw new ExceptionHandler(ErrorStatus.ALREADY_EXIST_FRIEND);
        }
    }
}
