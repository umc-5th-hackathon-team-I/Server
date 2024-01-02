package com.teami.domain.member.entitty;

import com.teami.domain.member.dto.request.MemberRequest;
import com.teami.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Where(clause = "deleted_at is null")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String pasword;

    @Column(nullable = false)
    private String refreshToken;


    public Member(MemberRequest req){
        this.loginId = req.getLoginId();
        this.nickname = req.getNickname();
        this.pasword = req.getPassword();
        this.refreshToken = "ddddd";

    }
}
