package com.dotori.backend.domain.script.model.entity;

import com.dotori.backend.domain.role.model.entity.Role;
import com.dotori.backend.domain.scene.model.entity.Scene;
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
@Table(name = "script")
public class Script {
    @Id
    @Column(name = "script_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long scriptId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "scene_id", nullable = false)
    private Scene scene;

    @Column(name = "script_order")
    private int scriptOrder;

    @Column(length = 100, name = "content")
    private String content;

    @Builder
    public Script(Role role, Scene scene, int scriptOrder, String content) {
        this.role = role;
        this.scene = scene;
        this.scriptOrder = scriptOrder;
        this.content = content;
    }
}
