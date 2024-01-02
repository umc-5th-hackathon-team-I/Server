package com.teami.domain.friend.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    boolean existsByMember1_IdAndAndMember2_Id(Long member1Id, Long member2Id);
}