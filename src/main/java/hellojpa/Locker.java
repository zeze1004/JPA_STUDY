package hellojpa;

import javax.persistence.*;

@Entity
public class Locker {
    @Id @GeneratedValue
    private Long id;
    private String name;

    // 양방향 관계 만들기
    @OneToOne(mappedBy = "locker") // Member에 있는 locker
    private Member member;
}
