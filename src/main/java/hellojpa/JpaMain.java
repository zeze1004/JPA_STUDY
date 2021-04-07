package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // jpa는 Persistence 클래스에서 시작
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");// unit name 받기
        EntityManager em = emf.createEntityManager();

        // 엔티티 매니저가 관리하는 엔티티는 트랜잭션 시작과 끝 사이에서 변경 되어야 함
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            // 팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            // 회원 저장
            Member member = new Member();
            member.setUsername("ZEZE1");
            // team에서 pk를 member에 join 해줌
            member.changeTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            // * 특정 멤버가 속한 팀의 팀원 이름 리스트*
            // 조회
            Member findMember = em.find(Member.class, member.getId());
            // 찾은 멤버의 아이디가 속한 팀을 찾고 그 팀의 멤버를 리스트에 담기
            List<Member> members = findMember.getTeam().getMembers();

            for (Member m : members) {
                System.out.println(m.getUsername());
            }

            // * 특정 멤버의 팀 바꾸기 *
            Team newTeam = em.find(Team.class, 100L);
            findMember.setTeam(newTeam);
            em.persist(newTeam);



            // 트랜직션 커밋할 때 DB에 영속성 컨택스트 저장
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
