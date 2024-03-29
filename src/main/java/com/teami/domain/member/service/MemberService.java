package com.teami.domain.member.service;

import com.teami.domain.calendar.entitty.CalendarVisitor;
import com.teami.domain.calendar.repository.CalendarVisitorRepository;
import com.teami.domain.friend.repository.FriendRepository;
import com.teami.domain.member.dto.request.LoginRequest;
import com.teami.domain.member.dto.request.MemberRequest;
import com.teami.domain.member.dto.request.VisitorCommentReq;
import com.teami.domain.member.dto.response.VisitorCommentRes;
import com.teami.domain.member.entitty.Member;
import com.teami.domain.member.repository.MemberRepository;
import com.teami.domain.reward.entity.MemberReward;
import com.teami.domain.reward.entity.Reward;
import com.teami.domain.reward.repository.MemberRewardRepository;
import com.teami.domain.reward.service.RewardService;
import com.teami.global.apiPayload.ExceptionHandler;
import com.teami.global.apiPayload.code.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.teami.domain.reward.entity.Reward.*;
import static com.teami.domain.reward.entity.Reward.SIGNUP_AND_CALENDAR_CREATE;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final FriendRepository friendRepository;
    private final CalendarVisitorRepository calendarVisitorRepository;
    private final MemberRewardRepository memberRewardRepository;

    public Member findMemberById(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isEmpty()) throw new ExceptionHandler(ErrorStatus.MEMBER_NOT_FOUND);
        return member.get();
    }

    public Member findById(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isEmpty()) {
            throw new ExceptionHandler(ErrorStatus.MEMBER_NOT_FOUND);
        }
        return member.get();
    }

    public Member addMember(MemberRequest memberRequest){
        String loginId = memberRequest.getLoginId();
        String nickname = memberRequest.getNickname();
        String pw = memberRequest.getPassword();

        //pw 암호화하는 과정 필요!!!!!!!!!!

        //아이디가 존재하면
        if(memberRepository.findMemberByLoginId(loginId) != null){
            throw new ExceptionHandler(ErrorStatus.MEMBER_FOUND);
        }
        //닉네임이 존재하면
        else if(memberRepository.findMemberByNickname(nickname) != null){
            throw new ExceptionHandler(ErrorStatus.NICKNAME_EXIST);
        }


        Member member = new Member(memberRequest);

        Member newMember =  memberRepository.save(member);

        createReward_Reward(member);

        if (!memberRewardRepository.existsByMemberAndReward(member, SIGNUP_AND_CALENDAR_CREATE)) {
            memberRewardRepository.save(MemberReward.createMemberReward(member, SIGNUP_AND_CALENDAR_CREATE));
        }

        return newMember;
    }

    public Member login(LoginRequest loginRequest) {
        String loginId = loginRequest.getLoginId();
        String pw = loginRequest.getPassword();

        System.out.println(loginId);
        Member existMember = memberRepository.findMemberByLoginId(loginId);
        System.out.println(existMember);

        return Optional.ofNullable(existMember)
                .filter(member -> pw.equals(member.getPassword()))
                .orElseThrow(() -> new ExceptionHandler(existMember != null ? ErrorStatus.PASSWORD_NOT_FOUND : ErrorStatus.MEMBER_NOT_FOUND));
    }

    public Boolean addVisitorComment(VisitorCommentReq visitorCommentReq) {


        Optional<Member> writer = memberRepository.findById(visitorCommentReq.getWriterId());
        Optional<Member> owner = memberRepository.findById(visitorCommentReq.getOwnerId());

        if (writer.isEmpty() || owner.isEmpty()) {
            throw new ExceptionHandler(ErrorStatus.FRIEND_NOT_FOUND);
        }

        if (friendRepository.findFriendByMember1AndMember2(owner.get(),writer.get()).isEmpty()){
            throw new ExceptionHandler(ErrorStatus.FRIEND_NOT_FOUND);
        }
        else {
            CalendarVisitor calendarVisitor = new CalendarVisitor(visitorCommentReq, owner.get(),writer.get());
            calendarVisitorRepository.save(calendarVisitor);
            return true;
        }
    }

    public List<VisitorCommentRes> getVisitCommentList(Long memberId) {
        if(memberRepository.findById(memberId).isEmpty()){
            throw new ExceptionHandler(ErrorStatus.MEMBER_NOT_FOUND);
        }
        List<CalendarVisitor> list = calendarVisitorRepository.findByOwnerId(memberId);

        List<VisitorCommentRes> dtoList = new ArrayList<>();

        for(CalendarVisitor l: list){
            dtoList.add(new VisitorCommentRes(l));
        }
        return  dtoList;

    }

    public VisitorCommentRes getVisitComment(Long memberId, Long calendarVisitorId) {
        if(memberRepository.findById(memberId).isEmpty()){
            throw new ExceptionHandler(ErrorStatus.MEMBER_NOT_FOUND);
        }
        Optional<CalendarVisitor> v = calendarVisitorRepository.findById(calendarVisitorId);
        if(v.isEmpty()){
            throw new ExceptionHandler(ErrorStatus.VISITOR_NOT_FOUND);
        }

        VisitorCommentRes res = new VisitorCommentRes(v.get(), true);

        return res;
    }

    public String checkFirstLetter(Long memberId) {
        if (memberRepository.findById(memberId).isEmpty()){
            throw new ExceptionHandler(ErrorStatus.MEMBER_NOT_FOUND);
        }
        int check = calendarVisitorRepository.checkFirstLetterByOwnerId(memberId);
        if (check == 1) {
            return "first";
        }
        return null;
    }
    private Reward createReward_Reward(Member member) {
        if (!memberRewardRepository.existsByMemberAndReward(member, COMPLETE_FIRST_MISSION)) {
            if (memberRewardRepository.countMemberRewardByMember(member) == 0L) {
                memberRewardRepository.save(MemberReward.createMemberReward(member, EARN_FIVE_REWARDS));
                return EARN_FIVE_REWARDS;
            }
        }
        return null;
    }
}
