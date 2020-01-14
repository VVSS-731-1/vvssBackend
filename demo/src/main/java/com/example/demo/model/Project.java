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

    public Project(int id, String name, String description, Boolean status, Long duration, Industry industry, Customer customer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.duration = duration;
        this.industry = industry;
        this.customer = customer;
    }

    public Project() {
    }
}
