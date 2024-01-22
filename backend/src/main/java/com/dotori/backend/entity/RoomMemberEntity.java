package com.dotori.backend.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "Room_Member")
public class RoomMemberEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private RoomEntity roomId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity memberId;
}
