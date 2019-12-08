package com.example.demo.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@Data
public class ProfileDTO implements Serializable {

    private Integer id;
    private Integer regionId;
    private Integer consultingLevelId;
    private Integer userId;
    private byte[] imageURL;
    private Boolean status;

    //Contains the skillId as a key and the level as a value
    private Map<Integer, Integer> skillIds;

    public ProfileDTO(Integer id, Integer regionId, Integer consultingLevelId, Integer userId, byte[] imageURL, Boolean status, Map<Integer, Integer> skillIds) {
        this.id = id;
        this.regionId = regionId;
        this.consultingLevelId = consultingLevelId;
        this.userId = userId;
        this.imageURL = imageURL;
        this.status = status;
        this.skillIds = skillIds;
    }

    public ProfileDTO() {
    }
}