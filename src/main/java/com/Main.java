package com;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaStudy");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("팀 이름을 입력하세요:");
            String teamName = sc.next();

            Team team = new Team();
            team.setName(teamName);
            em.persist(team); // 팀 객체를 데이터베이스에 저장

            while (true) {
                System.out.println("팀 이름을 변경하시겠습니까? (y/n)");
                String changeTeamName = sc.next();
                if ("y".equals(changeTeamName)) {
                    System.out.println("새 팀 이름을 입력하세요:");
                    teamName = sc.next();
                    team.setName(teamName);
                    // 팀 이름 변경을 위해 팀 객체를 다시 저장할 필요는 없습니다.
                    // 변경 사항은 트랜잭션 커밋 시 자동으로 반영됩니다.
                }

                System.out.println("계속 입력하시겠습니까? (y/n)");
                String answer = sc.next();
                if ("n".equals(answer)) {
                    break;
                }
                System.out.println("이름을 입력하세요:");
                String name = sc.next();
                System.out.println("나이를 입력하세요:");
                int age = sc.nextInt();

                Member member = new Member();
                member.setName(name);
                member.setAge(age);
                member.setTeam(team); // 멤버에 팀 설정

                em.persist(member); // Member 객체를 데이터베이스에 저장
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
    }
}