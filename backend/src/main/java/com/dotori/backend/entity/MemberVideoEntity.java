package com.dotori.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "Member_Video")
public class MemberVideoEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long memberId;

    @Column(unique = true) // unique 제약조건 추가 => 중복값을 허용하지 않도록 강제한다.
    private String memberNickName;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

}
