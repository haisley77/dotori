package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class jpamain {
    public static void main(String[] args) {
        // 데이터 베이스가 하나씩 묶여서 돌아간다. EntityManagerFactory
        // "hello" 는 설정파일의 설정정보를 읽어와서 만든다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 고객의 요청이 올때마다 db 작업이 필요하면 entitymanager를 통해 작업을 수행한다.
        EntityManager em = emf.createEntityManager();
        // jpa의 모든 데이터 변경은 transaction 안에서 일어난다.
        EntityTransaction tx = em.getTransaction();
        tx.begin(); // transaction 시작

        try {
            // 영속
            //Member member = new Member(200L, "member200");
            //em.persist(member); // 영속 컨텍스트에 담긴다.

            //em.flush(); // 쿼리가 그냥 디비에 반영된다.

            System.out.println("==============");
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close();

    }
}
