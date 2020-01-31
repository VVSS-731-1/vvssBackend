package com.example.demo.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SkillWrapperDTO implements Serializable {

    private String skill;
    private Integer profile_id;
    private Integer skill_level;

}
