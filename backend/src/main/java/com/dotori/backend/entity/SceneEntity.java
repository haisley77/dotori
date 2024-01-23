package com.dotori.backend.entity;

import com.fasterxml.jackson.databind.node.BigIntegerNode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
@Table(name = "scene")
public class SceneEntity {
    @Id
    @Column(name = "scene_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sceneId;

    @Column(name = "scene_order")
    private int sceneOrder;

    @Column(name = "background_image")
    private String backgroundImage;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity bookEntity;

    public SceneEntity() {
        // 기본 생성자
    }
}
