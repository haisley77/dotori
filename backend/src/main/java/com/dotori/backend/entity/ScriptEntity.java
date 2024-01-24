package com.dotori.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "script")
public class ScriptEntity {
    @Id
    @Column(name = "script_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scriptId;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @ManyToOne
    @JoinColumn(name = "scene_id", nullable = false)
    private SceneEntity scene;

    @Column(name = "script_order")
    private int scriptOrder;

    @Column(name = "content")
    private String content;
}
