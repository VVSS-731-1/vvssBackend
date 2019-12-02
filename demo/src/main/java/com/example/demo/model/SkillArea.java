package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "skill_areas")
@Data
public class SkillArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Boolean status;

}
