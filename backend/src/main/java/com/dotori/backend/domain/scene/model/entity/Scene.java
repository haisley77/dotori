package com.dotori.backend.domain.scene.model.entity;

import static javax.persistence.GenerationType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dotori.backend.domain.book.model.entity.Book;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "scene")
public class Scene {
	@Id
	@Column(name = "scene_id")
	@GeneratedValue(strategy = IDENTITY)
	private Long sceneId;

	@Column(name = "scene_order")
	private int sceneOrder;

	@Column(name = "background_image")
	private String backgroundImage;

	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;

	@Builder
	public Scene(int sceneOrder, String backgroundImage, Book book) {
		this.sceneOrder = sceneOrder;
		this.backgroundImage = backgroundImage;
		this.book = book;
	}
}
