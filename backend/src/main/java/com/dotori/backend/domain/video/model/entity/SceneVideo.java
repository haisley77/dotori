package com.dotori.backend.domain.video.model.entity;

import com.dotori.backend.common.entity.BaseTimeEntity;
import com.dotori.backend.domain.room.model.entity.Room;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "scene_video")
public class SceneVideo extends BaseTimeEntity {
    @Id
    @Column(name = "scene_video_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sceneVideoId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "scene_order")
    private int sceneOrder;

    @Column(length = 100, name = "path")
    private String path;

    @Builder
    public SceneVideo(Room room, int sceneOrder, String path) {
        this.room = room;
        this.sceneOrder = sceneOrder;
        this.path = path;
    }
}
