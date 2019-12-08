package com.example.demo.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SkillDTO implements Serializable {

    private Integer id;
    private String name;
    private Boolean status;
    private Integer skillAreaId;

    public SkillDTO() {
    }

    public SkillDTO(Integer id, String name, Boolean status, Integer skillAreaId) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.skillAreaId = skillAreaId;
    }
}
