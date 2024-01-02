package com.teami.domain.member.repository;

import com.teami.domain.member.entitty.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
