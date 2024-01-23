package com.dotori.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
@Table(name = "avatar")
public class AvatarEntity {
    @Id
    @Column(name = "avatar_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long avatarId;

    @Column(unique = true)
    private String path;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity memberId;

}

