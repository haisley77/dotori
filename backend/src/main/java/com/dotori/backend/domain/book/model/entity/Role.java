package com.dotori.backend.domain.book.model.entity;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "role")
public class Role {
	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = IDENTITY)
	private Long roleId;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@Column(length = 10)
	private String name;

	@Column(length = 100, name = "mask_path")
	private String maskPath;

	@Builder
	public Role(Book book, String name, String maskPath) {
		this.book = book;
		this.name = name;
		this.maskPath = maskPath;
	}
}
