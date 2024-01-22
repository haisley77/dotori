package com.dotori.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Setter
@Getter
@Table(name = "Member")
public class MemberEntity {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(unique = true)
    private String nickname;

    @Column(name = "profile_img")
    private String profileImg;

    public MemberEntity() {
        // 기본 생성자
    }
}
