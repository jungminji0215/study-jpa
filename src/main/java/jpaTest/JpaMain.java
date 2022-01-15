package jpaTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        /*
            EntityManagerFactory 만드는 순간 데이터베이스 연결도 됨
         */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        // 완젼 중요 - 트랜젝션
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 회원 객체 생성
            Member member = new Member();

            member.setId(1L);
            member.setName("정민지");
            em.persist(member);

            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            // 종료 - 꼭 닫아주어야 함
            em.close();
        }
     
        emf.close();
    }
}
