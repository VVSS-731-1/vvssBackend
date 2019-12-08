package com.example.demo.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ConsultingLevelDTO implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private Boolean status;

    public ConsultingLevelDTO() {
    }

    public ConsultingLevelDTO(Integer id, String name, String description, Boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }
}
