package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "skills")
@Data
public class Skill {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "skill_area_id")
    private SkillArea skillArea;

}
