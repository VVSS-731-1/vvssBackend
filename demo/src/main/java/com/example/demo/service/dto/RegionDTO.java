package com.example.demo.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegionDTO implements Serializable {

    private Integer id;
    private String regionName;
    private Boolean status;

    public RegionDTO() {

    }

    public RegionDTO(Integer id, String regionName, Boolean status) {
        this.id = id;
        this.regionName = regionName;
        this.status = status;
    }
}
