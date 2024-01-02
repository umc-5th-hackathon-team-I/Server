package com.teami.domain.friend.repository;

import com.teami.domain.member.entitty.Friend;
import com.teami.domain.member.entitty.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    boolean existsByMember1_IdAndMember2_Id(Long member1Id, Long member2Id);
    Optional<Friend> findFriendByMember1AndMember2(Member member1, Member member2);}