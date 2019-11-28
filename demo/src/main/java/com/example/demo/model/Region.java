package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "regions")
@Data
public class Region{

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="name")
    private String regionName;

}
