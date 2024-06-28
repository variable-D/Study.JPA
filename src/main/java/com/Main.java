package com;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  /*  public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaStudy");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();*/

      /*  try {

            List<Member> members = new ArrayList<>();

            Scanner sc = new Scanner(System.in);

            while (true){
                System.out.println("계속 입력하시겠습니까? (y/n)");
                String answer = sc.next();
                if (answer.equals("n")){
                    break;
                }
                System.out.println("이름을 입력하세요");
                String name = sc.next();
                System.out.println("나이를 입력하세요");
                int age = sc.nextInt();
                Member member = new Member();
                member.setName(name);
                member.setAge(age);
                members.add(member);

                em.persist(member);  // Member 객체를 데이터베이스에 저장
            }

            em.flush();
            em.clear();

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }*/
}
