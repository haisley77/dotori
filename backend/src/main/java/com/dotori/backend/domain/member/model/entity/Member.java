package com.dotori.backend.domain.member.model.entity;

import com.dotori.backend.common.entity.BaseTimeEntity;
import com.dotori.backend.domain.room.model.entity.RoomMember;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "member")
public class Member extends BaseTimeEntity {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long memberId;

    @Column(unique = true)
    private String nickname;

    @Column(length = 100, name = "profile_img")
    private String profileImg;

    @OneToOne(fetch = LAZY, mappedBy = "member", cascade = ALL)
    private RoomMember roomMember;

    @Builder
    public Member(String nickname, String profileImg) {
        this.nickname = nickname;
        this.profileImg = profileImg;
    }
}

