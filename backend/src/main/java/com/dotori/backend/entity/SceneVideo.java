package com.dotori.backend.entity;

import static javax.persistence.FetchType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
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

	@Column(name = "path")
	private String path;
}
