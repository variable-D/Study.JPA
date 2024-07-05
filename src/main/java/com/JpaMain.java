package com;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaStudy");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Team team1 = new Team();
            team1.setName("TeamA");
            em.persist(team1);

            Team team2 = new Team();
            team2.setName("TeamB");
            em.persist(team2);

            Team team3 = new Team();
            team3.setName("TeamC");
            em.persist(team3);

            Member member1 = new Member();
            member1.setName("변동환");
            member1.setAge(29);
            member1.setTeam(team1);
            em.persist(member1);

            Member member2 = new Member();
            member2.setName("박동환");
            member2.setAge(30);
            member2.setTeam(team2);
            em.persist(member2);

            Member member3 = new Member();
            member3.setName("김동환");
            member3.setAge(31);
            member3.setTeam(team3);
            em.persist(member3);


            em.flush();
            em.clear();

        /*    Member member = em.find(Member.class, member1.getId()); // flush 와 clear를 진행을 했으면 무조건 DB에 접근을 해서 쿼리문을 날려야한다.
            System.out.println("member.id = " + member.getId());
            System.out.println("member.name = " + member.getName());*/

            Member findMember = em.getReference(Member.class, member1.getId());// 이렇게 하면 DB에 접근을 하지 않는다. 그래서 성능이 좋아진다.
            System.out.println("findMember.getClass() = " + findMember.getClass()); //프록시 클래스이다. 이유는 Member.class를 참조하는 것이 아니라 프록시 클래스를 참조하기 때문이다.

            // 프록시 클래스 내부에는 target이라는 필드가 있는데 이 필드는 실제 클래스를 참조하고 있다.

            System.out.println("findMember = " + findMember.getId()); // 이때는 DB에 접근을 하지 않는다. 이유는 이미 em.getReference를 통해서 영속성 컨텍스트에 저장이 되어있기 때문이다. 그래서 DB에 접근을 하지 않는다. 그래서 성능이 좋아진다.

            System.out.println("findMember = " + findMember.getName()); // 하지만 이때는 DB에 접근을 한다. 그 이유는 name을 가져오기 위해서 DB에 접근을 해야하기 때문이다. 그래서 DB에 접근을 한다. 그래서 성능이 좋아지지 않는다.

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
