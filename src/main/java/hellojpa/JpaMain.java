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
            Movie movie = new Movie();
            movie.setDirector("zeze_director");
            movie.setActor("kodahyun");
            movie.setName("전농동 백예린");

            em.persist(movie);


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
