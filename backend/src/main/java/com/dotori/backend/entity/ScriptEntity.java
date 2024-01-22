package com.dotori.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@Table(name = "Script")
public class ScriptEntity {
    @Id
    @Column(name = "script_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scriptId;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity roleentity;

    @ManyToOne
    @JoinColumn(name = "score_id", nullable = false)
    private MemberEntity scoreId;

    @Column(name = "script_order")
    private int scriptOrder;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public ScriptEntity() {
        // 기본 생성자
    }
}
