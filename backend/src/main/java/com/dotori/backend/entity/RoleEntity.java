package com.dotori.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
@Table(name = "role")
public class RoleEntity {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity bookentity;

    @Column
    private String name;

    @Column(name = "mask_path")
    private String maskPath;
}
