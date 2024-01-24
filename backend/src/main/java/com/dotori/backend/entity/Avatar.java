package com.dotori.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "avatar")
public class Avatar {
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
    private Member member;

}

