package com.dotori.backend.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "room_member")
public class RoomMember extends BaseTimeEntity{
    @Id
    @Column(name = "room_member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomMemberId;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "avatar_id")
    private Long avatarId;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "is_ready")
    private boolean isReady;
}
