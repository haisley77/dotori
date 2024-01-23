package com.dotori.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Table(name = "script")
public class ScriptEntity {
    @Id
    @Column(name = "script_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scriptId;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity roleentity;

    @ManyToOne
    @JoinColumn(name = "scene_id", nullable = false)
    private SceneEntity sceneId;

    @Column(name = "script_order")
    private int scriptOrder;

    @Column(name = "content")
    private String content;

    public ScriptEntity() {
        // 기본 생성자
    }
}
