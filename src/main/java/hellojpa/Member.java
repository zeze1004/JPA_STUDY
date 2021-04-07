package hellojpa;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "Member") // 디폴트로 클래스 이름을 그대로 사용
public class Member {
    @Id
    @GeneratedValue(strategy = )
    private Long id;

    // 컬럼 매핑
   @Column(name = "name", nullable = false)
    private String username;
}
