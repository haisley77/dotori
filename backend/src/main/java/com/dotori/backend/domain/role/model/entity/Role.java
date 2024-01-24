package com.dotori.backend.domain.role.model.entity;

import static javax.persistence.GenerationType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dotori.backend.domain.book.model.entity.Book;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "role")
public class Role {
	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = IDENTITY)
	private Long roleId;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@Column
	private String name;

	@Column(name = "mask_path")
	private String maskPath;
}
