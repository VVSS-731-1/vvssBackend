package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "skills")
@Getter
@Setter
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skill_area_id")
    private SkillArea skillArea;

}
