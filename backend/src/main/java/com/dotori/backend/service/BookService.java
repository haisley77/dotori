package com.dotori.backend.service;

import com.dotori.backend.entity.BookEntity;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class BookService {
    @PersistenceContext
    // @PersistenceContext는 스프링 프레임워크에서 제공하는 어노테이션으로,
    // JPA의 EntityManager를 주입받기 위해 사용
    private EntityManager em;

    public BookEntity createBook(String title, String bookImg, String author, int roleCnt){
        BookEntity book = new BookEntity(null, title, bookImg, author, roleCnt);
        em.persist(book);

        return book;
    }
}
