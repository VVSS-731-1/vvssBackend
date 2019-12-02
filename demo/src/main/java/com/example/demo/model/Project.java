package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "projects")
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Boolean status;

    @Column
    private Long duration;

    @ManyToOne
    @JoinColumn(name = "industry_id")
    private Industry industry;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
