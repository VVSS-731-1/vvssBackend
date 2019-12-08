package com.example.demo.service.dto;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class ProfileDTO {

    private Integer id;
    private Integer regionId;
    private Integer consultingLevelId;
    private Integer userId;
    private byte[] imageURL;
    private Boolean status;

    //Contains the skillId as a key and the level as a value
    private Map<Integer, Integer> skillIds;

    public ProfileDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getConsultingLevelId() {
        return consultingLevelId;
    }

    public void setConsultingLevelId(Integer consultingLevelId) {
        this.consultingLevelId = consultingLevelId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public byte[] getImageURL() {
        return imageURL;
    }

    public void setImageURL(byte[] imageURL) {
        this.imageURL = imageURL;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Map<Integer, Integer> getSkillProfileIds() {
        return skillIds;
    }

    public void setSkillProfileIds(Map<Integer, Integer> skillProfileIds) {
        this.skillIds = skillProfileIds;
    }

    @Override
    public String toString() {
        return "ProfileDTO{" +
                "id=" + id +
                ", regionId=" + regionId +
                ", consultingLevelId=" + consultingLevelId +
                ", userId=" + userId +
                ", imageURL=" + Arrays.toString(imageURL) +
                ", status=" + status +
                ", skillProfileIds=" + skillIds +
                '}';
    }
}