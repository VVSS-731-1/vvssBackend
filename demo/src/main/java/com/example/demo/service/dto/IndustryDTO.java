package com.example.demo.service.dto;

import lombok.Data;

@Data
public class IndustryDTO {
    private Integer id;
    private String name;
    private String description;
    private Boolean status;

    public IndustryDTO() {
    }

    public IndustryDTO(Integer id, String name, String description, Boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }
}
