package com.dotori.backend.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Table(name = "member")
public class MemberEntity {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(unique = true)
    private String nickname;

    @Column(name = "profile_img")
    private String profileImg;

    // 생성일시
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    // 수정일시
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public MemberEntity() {
        // 기본 생성자
    }
}
