package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "regions")
@Data
public class Region{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column
    private Boolean status;

}
