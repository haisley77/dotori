package com.dotori.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
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
    private BookEntity book;
}
