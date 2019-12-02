package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String username;

    @Column
    private String email;

    @Column(name = "is_admin")
    private Boolean admin;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_projects",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    private List<Project> projects;

    @Column
    private Boolean status;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "supervisors",
//            joinColumns = @JoinColumn(name = "supervisor_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
//    private Set<User> supervised;

}
