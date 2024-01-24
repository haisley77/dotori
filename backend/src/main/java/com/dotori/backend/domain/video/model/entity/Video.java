package com.dotori.backend.domain.video.model.entity;

import static javax.persistence.GenerationType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dotori.backend.common.entity.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "video")
public class Video extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "video_id")
	private Long videoId;

	@Column(name = "path")
	private String path;

	@Builder
	public Video(String path) {
		this.path = path;
	}
}
