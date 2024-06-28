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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);


            Member member1 = new Member();
            member1.setName("변동환");
            member1.setAge(29);
            member1.setTeam(team);
            em.persist(member1);

            em.flush();
            em.clear();

            Member reference = em.getReference(Member.class, member1.getId());
            System.out.println("reference = " + reference.getClass());
            System.out.println("reference.id = " + reference.getId());
            System.out.println("reference.name = " + reference.getName());

//            em.find(Member.class, member1.getId());

//            System.out.println("findMember1.id = " + member1.getId());
//            System.out.println("findMember1.name = " + member1.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
