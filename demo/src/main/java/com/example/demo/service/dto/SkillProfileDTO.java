package com.example.demo.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SkillProfileDTO implements Serializable {

    SkillDTO skill_id;
    ProfileDTO profile_id;
    Integer level;
}
