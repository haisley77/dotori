package com.dotori.backend.entity;


// import com.dotori.backend.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "member_tbl")
public class MemberEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long memberid;

    @Column(unique = true) // unique 제약조건 추가 => 중복값을 허용하지 않도록 강제한다.
    private String memberNickName;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    public MemberEntity(){}

    public Long getMemberid() {
        return memberid;
    }
    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public String getMemberNickName() {
        return memberNickName;
    }
    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }

    public String getMemberPassword() {
        return memberPassword;
    }
    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}