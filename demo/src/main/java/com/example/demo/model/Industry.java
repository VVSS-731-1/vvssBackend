package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "industries")
@Data
public class Industry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Boolean status;
}
