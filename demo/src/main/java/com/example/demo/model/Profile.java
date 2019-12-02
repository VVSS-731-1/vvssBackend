package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "profile_id", fetch = FetchType.EAGER)
    private Set<SkillProfile> skillProfiles;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "consulting_level_id")
    private ConsultingLevel consultingLevel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Lob
    @Column(name = "image", columnDefinition="BLOB")
    private byte[] image;

    @Column
    private Boolean status;

}
