package com.dotori.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "scene_video")
public class SceneVideoEntity {
    @Id
    @Column(name = "scene_video_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sceneVideoId;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private RoomEntity roomEntity;

    @Column(name = "scene_order")
    private int sceneOrder;

    @Column(name = "path")
    private String path;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public SceneVideoEntity() {
        // 기본 생성자
    }
}
