package com.dotori.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "scene_video")
public class SceneVideo extends BaseTimeEntity{
    @Id
    @Column(name = "scene_video_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sceneVideoId;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "scene_order")
    private int sceneOrder;

    @Column(name = "path")
    private String path;
}
