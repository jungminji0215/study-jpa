package jpaTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
            List<Member> findMemberAll = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1) // 페이징이 굉장히 쉽다.
                    .setMaxResults(2)
                    .getResultList();

            for(Member member : findMemberAll){
                System.out.println("회원: " + member.getName());
            }

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
