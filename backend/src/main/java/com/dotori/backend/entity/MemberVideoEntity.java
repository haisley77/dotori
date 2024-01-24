package com.dotori.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member_video")
public class MemberVideoEntity extends BaseTimeEntity{
    @Id
    @Column(name = "member_video_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberVideoId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private VideoEntity video;

    @Column
    private Long bookId;
}
