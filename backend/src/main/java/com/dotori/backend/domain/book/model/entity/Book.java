package com.dotori.backend.domain.book.model.entity;

import static javax.persistence.GenerationType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor // 아무런 매개변수가 없는 생성자
// @AllArgsConstructor : 클래스에 대해 모든 필드를 파라미터로 받는 생성자를 자동으로 생성
@Table(name = "book")
public class Book {
	@Id
	@Column(name = "book_id")
	@GeneratedValue(strategy = IDENTITY)
	private Long bookId;

	@Column
	private String title;

	@Column(name = "book_img")
	private String bookImg;

	@Column
	private String author;

	@Column(name = "role_cnt")
	private int roleCnt;
}
