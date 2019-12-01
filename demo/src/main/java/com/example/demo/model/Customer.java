package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column
    private Boolean status;

}
