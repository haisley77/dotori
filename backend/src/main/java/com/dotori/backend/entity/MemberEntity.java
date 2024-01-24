package com.dotori.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
public class MemberEntity extends BaseTimeEntity{
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(unique = true)
    private String nickname;

    @Column(name = "profile_img")
    private String profileImg;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private RoomMemberEntity roommember;
}

