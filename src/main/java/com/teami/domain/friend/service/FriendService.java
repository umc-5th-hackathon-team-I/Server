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

import java.util.Optional;

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
        if (friendRepository.existsByMember1_IdAndMember2_Id(member1Id, member2Id) ||
                friendRepository.existsByMember1_IdAndMember2_Id(member2Id, member1Id)) {
            throw new ExceptionHandler(ErrorStatus.ALREADY_EXIST_FRIEND);
        }
    }

    public Friend findFriendByMember1AndMember2(Member member1, Member member2) {
        Optional<Friend> friend = friendRepository.findFriendByMember1AndMember2(member1, member2);
        if (friend.isEmpty()) {
            friend = friendRepository.findFriendByMember1AndMember2(member2, member1);
        }
        if (friend.isEmpty()) {
            throw new ExceptionHandler(ErrorStatus.FRIEND_NOT_FOUND);
        }
        return friend.get();
    }

    @Transactional
    public void deleteFriend(Long requesterId, Long requestedId) {
        Member requester = memberService.findById(requesterId);
        Member requested = memberService.findById(requestedId);

        Friend friend = findFriendByMember1AndMember2(requester, requested);
        friend.markAsDeleted();
    }
}
