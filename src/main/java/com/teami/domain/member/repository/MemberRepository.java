package com.teami.domain.member.repository;

import com.teami.domain.member.entitty.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findMemberByLoginId(@Param("loginId") String loginId);

    Member findMemberByNickname(@Param("nickname") String nickname);
}
