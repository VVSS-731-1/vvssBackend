package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "skill_profiles")
@Data
@IdClass(SkillProfileKey.class)
public class SkillProfile {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    Skill skill_id;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    Profile profile_id;

    @Column(name = "level")
    Integer level;

    public SkillProfile(Skill skill_id, Profile profile_id, Integer level) {
        this.skill_id = skill_id;
        this.profile_id = profile_id;
        this.level = level;
    }

    public SkillProfile() {
    }
}
