package com.dotori.backend.entity;


// import com.dotori.backend.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Setter
@Getter
@Table(name = "Member")
public class MemberEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private BigInteger memberId;

    @Column(unique = true) // unique 제약조건 추가 => 중복값을 허용하지 않도록 강제한다.
    private String nickname;

    @Column
    private String profileImg;

    public MemberEntity(){}
}