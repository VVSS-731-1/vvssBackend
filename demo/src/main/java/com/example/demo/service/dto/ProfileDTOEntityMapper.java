package com.example.demo.service.dto;

import com.example.demo.model.Profile;
import com.example.demo.model.Skill;
import com.example.demo.model.SkillProfile;
import com.example.demo.repository.ConsultingLevelRepository;
import com.example.demo.repository.RegionRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProfileDTOEntityMapper {


    @Autowired
    private static RegionRepository regionRepository;

    @Autowired
    private static ConsultingLevelRepository consultingLevelRepository;

    @Autowired
    private static UserRepository userRepository;

    @Autowired
    private static SkillRepository skillRepository;

    private ProfileDTOEntityMapper() {

    }

    /**
     * Maps a profile dto to a profile entity
     * @param profileDTO profile dto to be mapped
     * @return a profile
     * @author Miruna
     */
    public static Profile getProfileFromProfileDTO(ProfileDTO profileDTO) {
        Profile profile = new Profile();

        if(profileDTO != null) {
            profile.setId(profileDTO.getId());

            Set<SkillProfile> skills = new HashSet<>();
            for(Map.Entry<Integer, Integer> entry : profileDTO.getSkillProfileIds().entrySet()) {
                Skill skill = skillRepository.getOne(entry.getKey());
                SkillProfile skillProfile = new SkillProfile();

                skillProfile.setSkill_id(skill);
                skillProfile.setProfile_id(profile);
                skillProfile.setLevel(entry.getValue());

                skills.add(skillProfile);
            }
            profile.setSkillProfiles(skills);
            regionRepository.findById(profileDTO.getRegionId()).ifPresent(profile::setRegion);
            consultingLevelRepository.findById(profileDTO.getConsultingLevelId()).ifPresent(profile::setConsultingLevel);
            userRepository.findById(profileDTO.getUserId()).ifPresent(profile::setUser);
            profile.setImage(profileDTO.getImageURL());
            profile.setStatus(profileDTO.getStatus());
        }

        return profile;
    }

    /**
     * Maps a profile entity to a profile dto
     * @param profile profile to be mapped
     * @return a profile dto
     * @author Miruna
     */
    public static ProfileDTO getDTOFromProfile(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();

        if(profile != null) {
            profileDTO.setId(profile.getId());

            Map<Integer, Integer> skills = new HashMap<>();
            for(SkillProfile skillProfile : profile.getSkillProfiles()) {
                skills.put(skillProfile.getSkill_id().getId(), skillProfile.getLevel());
            }
            profileDTO.setRegionId(profile.getRegion().getId());
            profileDTO.setConsultingLevelId(profile.getConsultingLevel().getId());
            profileDTO.setUserId(profile.getUser().getId());
            profileDTO.setImageURL(profile.getImage());
            profileDTO.setStatus(profile.getStatus());
        }

        return profileDTO;
    }
}
