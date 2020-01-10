package com.example.demo.service.dto;

import lombok.Data;

@Data
public class ProjectDto {
    private Integer id;
    private String name;
    private String description;
    private Boolean status;
    private Long duration;
    private Integer industryId;
    private Integer customerId;

    public ProjectDto() {
    }

    public ProjectDto(String name, String description, Boolean status, Long duration, Integer industryId, Integer customerId) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.duration = duration;
        this.industryId = industryId;
        this.customerId = customerId;
    }
}
