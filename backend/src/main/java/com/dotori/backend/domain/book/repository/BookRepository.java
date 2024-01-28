package com.dotori.backend.domain.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dotori.backend.domain.book.model.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
