package hellojpa;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "Member") // 디폴트로 클래스 이름을 그대로 사용
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    // 하나의 팀에 여러명의 멤버가 소속 되므로 member : team은 다대일 관계
    @ManyToOne
    @JoinColumn(name = "TEAM_ID") // Join 해야할 컬럼, TEAM의 PK를 가져옴
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKED_ID")
    private Locker locker;

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

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

    public Team getTeam() {
        return team;
    }
    // 연관관계 편의 메소드
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
