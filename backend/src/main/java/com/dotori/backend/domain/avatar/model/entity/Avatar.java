package com.dotori.backend.domain.avatar.model.entity;

import com.dotori.backend.domain.member.model.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "avatar")
public class Avatar {
    @Id
    @Column(name = "avatar_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long avatarId;

    @Column(length = 100, unique = true)
    private String path;

    @Column(length = 20)
    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public Avatar(String path, String name, Member member) {
        this.path = path;
        this.name = name;
        this.member = member;
    }
}

