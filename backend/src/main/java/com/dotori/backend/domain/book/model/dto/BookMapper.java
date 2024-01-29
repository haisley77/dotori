package com.dotori.backend.domain.book.model.dto;

import com.dotori.backend.domain.book.model.entity.Book;
import com.dotori.backend.domain.book.model.entity.Role;

public class BookMapper {
	public static BookDto toBookDto(Book book) {
		return BookDto.builder()
			.bookId(book.getBookId())
			.title(book.getTitle())
			.bookImg(book.getBookImg())
			.author(book.getAuthor())
			.summary(book.getSummary())
			.roleCnt(book.getRoleCnt())
			.build();
	}

	public static RoleDto toRoleDto(Role role) {
		return RoleDto.builder()
			.roleId(role.getRoleId())
			.name(role.getName())
			.maskPath(role.getMaskPath())
			.build();
	}
}
