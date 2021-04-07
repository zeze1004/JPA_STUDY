package hellojpa;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "Member") // 디폴트로 클래스 이름을 그대로 사용
public class Member extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    // 근무 시간
    @Embedded
    private Period workPeriod;

    // 여가 시간
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "startDate",
                column = @Column(name = "rest_start_time")),
            @AttributeOverride(name = "endDate",
                    column = @Column(name = "rest_end_time"))
    })
    private Period workPeriod;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }
}
