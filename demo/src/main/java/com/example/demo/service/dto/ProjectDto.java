package com.example.demo.service.dto;

import lombok.Data;

@Data
public class ProjectDto {
    private Integer id;
    private String name;
    private String description;
    private Boolean status;
    private Long duration;
    private IndustryDto industry;
    private CustomerDto customer;

    public ProjectDto() {
    }

    public ProjectDto(String name, String description, Boolean status, Long duration, IndustryDto industry, CustomerDto customer) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.duration = duration;
        this.industry = industry;
        this.customer = customer;
    }
}
