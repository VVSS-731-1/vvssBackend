package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.dto.ProfileDTO;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.interfaces.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Component
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ConsultingLevelRepository consultingLevelRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    DtoMapping dtoMapping;

    public List<Profile> findAll()
    {
        return profileRepository.findAll();
    }

    public ProfileDTO findById(Integer id) {
        if(profileRepository.findById(id).isPresent()){
            return dtoMapping.getDTOFromProfile(profileRepository.findById(id).get());
        }

        return null;
    }

    public List<ProfileDTO> findByUsername(String username) {

        List<Profile> profiles = profileRepository.findAll().stream()
                .filter(profile -> profile.getUser().getUsername().equals(username)).collect(Collectors.toList());

        if(!profiles.isEmpty()) {
            return profiles.stream().map(profile -> dtoMapping.getDTOFromProfile(profile)).collect(Collectors.toList());
        }

        return null;
    }

    /**
     * Insert new profile into DB
     * @param profileDTO profile to be inserted
     * @return the profile dto
     * @author Miruna
     */
    public ProfileDTO saveProfile(ProfileDTO profileDTO) {
        Profile profile = dtoMapping.getProfileFromProfileDTO(profileDTO);
        profile = profileRepository.save(profile);

        return dtoMapping.getDTOFromProfile(profile);
    }

    /**
     * Update the profile
     * @param profileDTO the profile to be updated
     * @return profile dto
     * @author Miruna
     */
    public ProfileDTO updateProfile(ProfileDTO profileDTO) {
        Profile profile = profileRepository.getOne(profileDTO.getId());
        Region region = regionRepository.getOne(profileDTO.getRegionId());
        ConsultingLevel consultingLevel = consultingLevelRepository.getOne(profileDTO.getConsultingLevelId());
        User user = userRepository.getOne(profileDTO.getUserId());
        byte[] image = profileDTO.getImageURL();

        profile.setRegion(region);
        profile.setConsultingLevel(consultingLevel);
        profile.setUser(user);
        profile.setImage(image);

        Map<Integer, Integer> skillIds = profileDTO.getSkillIds();
        Set<SkillProfile> skillProfiles = new HashSet<>();

        for(Map.Entry<Integer, Integer> entry : skillIds.entrySet()) {
            Skill skill = skillRepository.getOne(entry.getKey());
            SkillProfile skillProfile = new SkillProfile();

            skillProfile.setSkill_id(skill);
            skillProfile.setProfile_id(profile);
            skillProfile.setLevel(entry.getValue());

            skillProfiles.add(skillProfile);
        }

        profile.setSkillProfiles(skillProfiles);
        profileRepository.save(profile);
        profileRepository.flush();

        return dtoMapping.getDTOFromProfile(profile);
    }

    /**
     * Sets the status to false = inactive
     * @param profileDTO the profile that will be "deleted"
     * @return the profile DTO
     * @author Miruna
     */
    public void deactivate(ProfileDTO profileDTO) {
        Profile profile = profileRepository.getOne(profileDTO.getId());

        if(profile != null) {
            profile.setStatus(false);
            profileRepository.flush();
        }
    }

    public void assignRegion(ProfileDTO profileDTO) {
        profileRepository.findById(profileDTO.getId()).ifPresent(profile -> {
            Profile profileToAssign = dtoMapping.getProfileFromProfileDTO(profileDTO);
            profile.setRegion(profileToAssign.getRegion());
            profileRepository.save(profile);
        });
    }

    public void assignConsultingLevel(ProfileDTO profileDTO) {
        profileRepository.findById(profileDTO.getId()).ifPresent(profile -> {
            Profile profileToAssign = dtoMapping.getProfileFromProfileDTO(profileDTO);
            profile.setConsultingLevel(profileToAssign.getConsultingLevel());
            profileRepository.save(profile);
        });
    }

    public void assignSkillProfile(ProfileDTO profileDTO) {
        profileRepository.findById(profileDTO.getId()).ifPresent(profile -> {
            Profile profileToAssign = dtoMapping.getProfileFromProfileDTO(profileDTO);
            profile.setSkillProfiles(profileToAssign.getSkillProfiles());
            profileRepository.save(profile);
        });
    }
}
