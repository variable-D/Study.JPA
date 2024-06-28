package com;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// @Entity: JPA가 관리하는 객체, 테이블과 매핑
@Entity
@Setter
@Getter
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer age;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

}
