package com.teami.domain.member.service;


import com.teami.domain.member.dto.LoginRequest;
import com.teami.domain.member.dto.LoginResponse;
import com.teami.domain.member.dto.MemberRequest;
import com.teami.domain.member.entitty.Member;
import com.teami.domain.member.repository.MemberRepository;
import com.teami.global.apiPayload.ExceptionHandler;
import com.teami.global.apiPayload.code.status.ErrorStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public boolean addMember(MemberRequest memberRequest){
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

        memberRepository.save(member);

        return true;
    }


    public Member login(LoginRequest loginRequest) {
        String loginId = loginRequest.getLoginId();
        String pw = loginRequest.getPassword();

        System.out.println(loginId);
        Member existMember = memberRepository.findMemberByLoginId(loginId);
        System.out.println(existMember);


        if(existMember != null){
            if(pw.equals(existMember.getPasword())){
                return existMember;
            }
            else{
                throw new ExceptionHandler(ErrorStatus.PASSWORD_NOT_FOUND);
            }
        }
        else{
            throw new ExceptionHandler(ErrorStatus.MEMBER_NOT_FOUND);
        }
    }
}
