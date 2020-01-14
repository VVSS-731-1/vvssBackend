package com.example.demo.service.dto;

import lombok.Data;

@Data
public class ProjectDTO {
    private Integer id;
    private String name;
    private String description;
    private Boolean status;
    private Long duration;
    private Integer industryId;
    private Integer customerId;

    public ProjectDTO() {
    }

    public ProjectDTO(String name, String description, Boolean status, Long duration, Integer industryId, Integer customerId) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.duration = duration;
        this.industryId = industryId;
        this.customerId = customerId;
    }
}
