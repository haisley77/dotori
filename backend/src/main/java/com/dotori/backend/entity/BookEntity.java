package com.dotori.backend.entity;

import lombok.Getter;
import javax.persistence.*;

@Entity
@Getter
@Table(name = "book")
public class BookEntity {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
