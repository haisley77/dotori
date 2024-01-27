package com.dotori.backend.domain.book.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long bookId;

    @Column(length = 20)
    private String title;

    @Column(length = 100, name = "book_img")
    private String bookImg;

    @Column(length = 20)
    private String author;

    @Column(name = "role_cnt")
    private int roleCnt;

    @Builder
    public Book(String title, String bookImg, String author) {
        this.title = title;
        this.bookImg = bookImg;
        this.author = author;
    }
}
