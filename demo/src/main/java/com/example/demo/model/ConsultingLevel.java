package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "consulting_level")
@Data
public class ConsultingLevel {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Boolean status;

}
