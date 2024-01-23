package com.dotori.backend.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "room_Member")
public class RoomMemberEntity {
    @Id
    @Column(name = "room_member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomMemberId;

    @Column(name = "role_id")
    private BigInteger roleId;

    @Column(name = "avatar_id")
    private BigInteger avatarId;

    @OneToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @Column(name = "is_ready")
    private boolean isReady;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public RoomMemberEntity() {
        // 기본 생성자
    }
}
