package com.dotori.backend.domain.video.model.entity;

import com.dotori.backend.common.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "video")
public class Video extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "video_id")
    private Long videoId;

    @Column(length = 100, name = "path")
    private String path;

    @Builder
    public Video(String path) {
        this.path = path;
    }
}
