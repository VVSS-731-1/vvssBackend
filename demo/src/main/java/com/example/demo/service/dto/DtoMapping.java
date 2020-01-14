package com.example.demo.service.dto;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.SkillAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DtoMapping {


    @Autowired
    private static RegionRepository regionRepository;

    @Autowired
    private static ConsultingLevelRepository consultingLevelRepository;

    @Autowired
    private static UserRepository userRepository;

    @Autowired
    private static SkillRepository skillRepository;

    @Autowired
    private static SkillAreaRepository skillAreaRepository;

    @Autowired
    private static IndustryRepository industryRepository;

    @Autowired
    private static CustomerRepository customerRepository;

    @Autowired
    private static SkillAreaService skillAreaService;

    private DtoMapping() {
        //skillAreaService = new SkillAreaService();
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
            for(Map.Entry<Integer, Integer> entry : profileDTO.getSkillIds().entrySet()) {
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

    public static ConsultingLevelDTO getDTOFromConsultingLevel(ConsultingLevel consultingLevel) {
        ConsultingLevelDTO consultingLevelDTO = new ConsultingLevelDTO();

        if(consultingLevel != null) {
            consultingLevelDTO.setId(consultingLevel.getId());
            consultingLevelDTO.setName(consultingLevel.getName());
            consultingLevelDTO.setDescription(consultingLevel.getDescription());
            consultingLevelDTO.setStatus(consultingLevel.getStatus());
        }

        return consultingLevelDTO;
    }

    public static ConsultingLevel getConsultingLevelFromDTO(ConsultingLevelDTO consultingLevelDTO) {
        ConsultingLevel consultingLevel = new ConsultingLevel();

        if(consultingLevelDTO != null) {
            consultingLevel.setId(consultingLevelDTO.getId());
            consultingLevel.setName(consultingLevelDTO.getName());
            consultingLevel.setDescription(consultingLevelDTO.getDescription());
            consultingLevel.setStatus(consultingLevelDTO.getStatus());
        }

        return consultingLevel;
    }

    public static RegionDTO getDTOFromRegion(Region region) {
        RegionDTO regionDTO = new RegionDTO();

        if(region != null) {
            regionDTO.setId(region.getId());
            regionDTO.setRegionName(region.getRegionName());
            regionDTO.setStatus(region.getStatus());
        }

        return regionDTO;
    }


    public static Region getRegionFromDTO(RegionDTO regionDTO) {
        Region region = new Region();

        if(regionDTO != null) {
            region.setId(regionDTO.getId());
            region.setRegionName(regionDTO.getRegionName());
            region.setStatus(regionDTO.getStatus());
        }

        return region;
    }

    public static SkillDTO getDTOFromSkill(Skill skill) {
        SkillDTO skillDTO = new SkillDTO();

        if(skill != null) {
            skillDTO.setId(skill.getId());
            skillDTO.setName(skill.getName());
            skillDTO.setStatus(skill.getStatus());
            skillDTO.setSkillAreaId(skill.getSkillArea().getId());
        }

        return skillDTO;
    }

    public static Skill getSkillFromDto(SkillDTO skillDTO) {
        Skill skill = new Skill();
        //skillAreaService = new SkillAreaService();

        if(skillDTO != null) {
            skill.setId(skillDTO.getId());
            skill.setName(skillDTO.getName());
            skill.setStatus(skillDTO.getStatus());
//            Optional<SkillArea> skillArea = skillAreaRepository.findById(skillDTO.getSkillAreaId());//.ifPresent(skill::setSkillArea);
//            skill.setSkillArea(skillArea.get());
//            SkillAreaDTO skillAreaDTO = skillAreaService.getSkillAreaById(skillDTO.getSkillAreaId());
//            skill.setSkillArea(getSkillAreaFromDTO(skillAreaDTO));

            SkillArea skillArea = skillAreaRepository.getOne(skillDTO.getSkillAreaId());
            skill.setSkillArea(skillArea);
        }

        return skill;
    }

    public static SkillAreaDTO getDTOFromSkillArea(SkillArea skillArea) {
        SkillAreaDTO skillAreaDTO = new SkillAreaDTO();

        if(skillArea != null){
            skillAreaDTO.setId(skillArea.getId());
            skillAreaDTO.setName(skillArea.getName());
            skillAreaDTO.setStatus(skillArea.getStatus());
        }

        return skillAreaDTO;
    }

    public static SkillArea getSkillAreaFromDTO(SkillAreaDTO skillAreaDTO) {
        SkillArea skillArea = new SkillArea();

        if(skillAreaDTO != null) {
            skillArea.setId(skillAreaDTO.getId());
            skillArea.setName(skillAreaDTO.getName());
            skillArea.setStatus(skillAreaDTO.getStatus());
        }

        return skillArea;
    }

    public static Project dtoToProject(ProjectDTO projectDto) {
        Project project = new Project();
        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setDuration(projectDto.getDuration());
        project.setStatus(projectDto.getStatus());
        customerRepository.findById(projectDto.getCustomerId()).ifPresent(project::setCustomer);
        industryRepository.findById(projectDto.getIndustryId()).ifPresent(project::setIndustry);
        return project;
    }

    public static ProjectDTO projectToDto(Project project) {
        ProjectDTO projectDto = new ProjectDTO();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setDescription(project.getDescription());
        projectDto.setDuration(project.getDuration());
        projectDto.setStatus(project.getStatus());
        projectDto.setCustomerId(project.getCustomer().getId());
        projectDto.setIndustryId(project.getIndustry().getId());
        return projectDto;
    }

    public static Customer dtoToCustomer(CustomerDTO customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setStatus(customerDto.getStatus());
        return customer;
    }

    public static CustomerDTO customerToDto(Customer customer) {
        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setStatus(customer.getStatus());
        return customerDto;
    }

    public static Industry dtoToIndustry(IndustryDTO industryDto) {
        Industry industry = new Industry();
        industry.setId(industryDto.getId());
        industry.setName(industryDto.getName());
        industry.setDescription(industryDto.getDescription());
        industry.setStatus(industryDto.getStatus());
        return industry;
    }

    public static IndustryDTO industryToDto(Industry industry) {
        IndustryDTO industryDto = new IndustryDTO();
        industryDto.setId(industry.getId());
        industryDto.setName(industry.getName());
        industryDto.setDescription(industry.getDescription());
        industryDto.setStatus(industry.getStatus());
        return industryDto;
    }

}
