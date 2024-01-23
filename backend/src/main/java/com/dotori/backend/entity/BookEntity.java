package com.dotori.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
// @AllArgsConstructor : 클래스에 대해 모든 필드를 파라미터로 받는 생성자를 자동으로 생성
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

    public BookEntity() {

    }
}
