package com.example.demo.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SkillAreaDTO implements Serializable {

    private Integer id;
    private String name;
    private Boolean status;

    public SkillAreaDTO() {
    }

    public SkillAreaDTO(Integer id, String name, Boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}
