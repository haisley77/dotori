package jpa;


import javax.persistence.Entity;
import javax.persistence.Id;

//jpa 가 관리하는 객체 , db 테이블과 매핑해서 사용한다.
@Entity
//@Table(name="USER") // 테이블을 다른 이름으로 매핑하고 싶을때. db의 user 테이블과 매핑한다.
public class Member {
    @Id // PK 를 알려준다.
    private Long id;
    private String name;
    private int age;

    public Member(){
    }

    public Member(Long id, String name){
        this.id = id;
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
