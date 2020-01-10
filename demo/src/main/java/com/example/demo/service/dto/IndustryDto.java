package com.example.demo.service.dto;

import lombok.Data;

@Data
public class IndustryDto {
    private Integer id;
    private String name;
    private String description;
    private Boolean status;

    public IndustryDto() {
    }

    public IndustryDto(Integer id, String name, String description, Boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }
}
