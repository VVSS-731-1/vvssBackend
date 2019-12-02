package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Boolean status;

}
