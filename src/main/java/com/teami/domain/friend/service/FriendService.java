package com.teami.domain.friend.service;

import com.teami.domain.calendar.entitty.Calendar;
import com.teami.domain.calendar.entitty.CalendarMission;
import com.teami.domain.calendar.repository.CalendarMissionRepository;
import com.teami.domain.calendar.repository.CalendarRepository;
import com.teami.domain.friend.controller.dto.response.FriendCalendarInfo;
import com.teami.domain.friend.controller.dto.response.FriendListResponse;
import com.teami.domain.friend.controller.dto.response.FriendMissionInfo;
import com.teami.domain.friend.entity.Friend;
import com.teami.domain.friend.repository.FriendRepository;
import com.teami.domain.member.entitty.Member;
import com.teami.domain.member.service.MemberService;
import com.teami.global.apiPayload.ExceptionHandler;
import com.teami.global.apiPayload.code.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final MemberService memberService;
    private final CalendarRepository calendarRepository;
    private final CalendarMissionRepository calendarMissionRepository;

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
        return friendRepository.findFriendByMember1AndMember2(member1, member2)
                .or(() -> friendRepository.findFriendByMember1AndMember2(member2, member1))
                .orElseThrow(() -> new ExceptionHandler(ErrorStatus.FRIEND_NOT_FOUND));
    }

    public List<Long> findFriendsIdByMemberId(Long memberId) {
        List<Long> list1 = friendRepository.findFriendIdByMember1Id(memberId);
        List<Long> list2 = friendRepository.findFriendIdByMember2Id(memberId);
        return Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteFriend(Long requesterId, Long requestedId) {
        Member requester = memberService.findById(requesterId);
        Member requested = memberService.findById(requestedId);

        Friend friend = findFriendByMember1AndMember2(requester, requested);
        friend.markAsDeleted();
    }

    private void validateMember(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new ExceptionHandler(ErrorStatus.MEMBER_NOT_FOUND);
        }
    }

    public FriendListResponse getFriendList(Long memberId) {
        validateMember(memberId);
        return new FriendListResponse(memberId, findFriendsIdByMemberId(memberId));
    }

    private void validateFriend(Long member1Id, Long member2Id) {
        if (!friendRepository.existsByMember1_IdAndMember2_Id(member1Id, member2Id)
                && !friendRepository.existsByMember1_IdAndMember2_Id(member2Id, member1Id)) {
            throw new ExceptionHandler(ErrorStatus.FRIEND_NOT_FOUND);
        }
    }

    public FriendCalendarInfo getCalendarInfo(Long memberId, Long friendMemberId) {
        validateFriend(memberId, friendMemberId);
        Member friendMember = memberService.findById(friendMemberId);

        Long calendarId = calendarRepository.findByMemberAndIsComplete(friendMember, false).get().getId();
        return new FriendCalendarInfo(memberId, friendMemberId, calendarId);
    }

    public FriendMissionInfo getMissionInfo(Long memberId, Long friendMemberId) {
        validateFriend(memberId, friendMemberId);
        Member friendMember = memberService.findById(friendMemberId);

        Optional<Calendar> calendar = calendarRepository.findByMemberAndIsComplete(friendMember, false);
        List<CalendarMission> friendMissions = calendarMissionRepository.findAllByCalendarOrderByDate(calendar.get());

        FriendMissionInfo friendMissionInfo = new FriendMissionInfo(memberId, friendMemberId, friendMissions);
        return friendMissionInfo;
    }
}
