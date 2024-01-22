package com.dotori.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Setter
@Getter
@Table(name = "Avatar")
public class AvatorEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private BigInteger avatarId;
    @Column(unique = true)
    private String path;
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity memberId;

}
