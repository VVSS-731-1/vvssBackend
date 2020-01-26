package com.example.demo.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class ProfileDTO implements Serializable {

    private Integer id;
    private RegionDTO region;
    private ConsultingLevelDTO consultingLevel;
    private UserDTO user;
    private byte[] imageURL;
    private Boolean status;

    //Contains the skillId as a key and the level as a value
    private Map<SkillDTO, Integer> skillLevels;

    public ProfileDTO(Integer id, RegionDTO region, ConsultingLevelDTO consultingLevel, UserDTO user, byte[] imageURL, Boolean status, Map<SkillDTO, Integer> skillLevels) {
        this.id = id;
        this.region = region;
        this.consultingLevel = consultingLevel;
        this.user = user;
        this.imageURL = imageURL;
        this.status = status;
        this.skillLevels = skillLevels;
    }

    public ProfileDTO() {
    }
}