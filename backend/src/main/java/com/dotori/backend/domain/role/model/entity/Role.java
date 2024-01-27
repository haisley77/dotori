package com.dotori.backend.domain.role.model.entity;

import com.dotori.backend.domain.book.model.entity.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

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
