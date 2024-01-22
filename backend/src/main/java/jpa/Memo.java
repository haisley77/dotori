package jpa;
import lombok.*;
import javax.persistence.*;

@Entity

@Table(name = "tbl_memo")

@Data

@Builder
@AllArgsConstructor // @Builder 를 이용하기 위해서 항상 같이 처리해야 컴파일 에러가 발생하지 않는다
@NoArgsConstructor // @Builder 를 이용하기 위해서 항상 같이 처리해야 컴파일 에러가 발생하지 않는다
public class Memo {

    @Id // @Entity 가 붙은 클래스는 PK에 해당하는 특정필드를 @Id로 지정해야 한다
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memo;

    @Column
    private String memoText;

}