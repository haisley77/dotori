package com.dotori.backend.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@Table(name = "Member_Video")
public class MemberVideoEntity {
    @Id
    @Column(name = "member_video_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberVideoId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity memberId;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private VideoEntity videoId;

    @Column
    private BigInteger bookId;

    // 생성일시
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    // 수정일시
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
