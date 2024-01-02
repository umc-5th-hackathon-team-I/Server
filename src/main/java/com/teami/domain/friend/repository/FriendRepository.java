package com.teami.domain.friend.repository;

import com.teami.domain.friend.entity.Friend;
import com.teami.domain.member.entitty.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    @Query(value = "SELECT member2_id FROM friend WHERE member1_id = :member1Id", nativeQuery = true)
    List<Long> findFriendIdByMember1Id(@Param("member1Id") Long member1Id);

    @Query(value = "SELECT member1_id FROM friend WHERE member2_id = :member2Id", nativeQuery = true)
    List<Long> findFriendIdByMember2Id(@Param("member2Id") Long member2Id);

    boolean existsByMember1_IdAndMember2_Id(Long member1Id, Long member2Id);

    Optional<Friend> findFriendByMember1AndMember2(Member member1, Member member2);
}