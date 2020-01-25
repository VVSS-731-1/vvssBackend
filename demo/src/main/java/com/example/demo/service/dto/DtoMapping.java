package com.example.demo.service.dto;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DtoMapping {


    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ConsultingLevelRepository consultingLevelRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SkillAreaRepository skillAreaRepository;

    @Autowired
    private IndustryRepository industryRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private DtoMapping() {

    }

    /**
     * Maps a profile dto to a profile entity
     *
     * @param profileDTO profile dto to be mapped
     * @return a profile
     * @author Miruna
     */
    public Profile getProfileFromProfileDTO(ProfileDTO profileDTO) {
        Profile profile = new Profile();

        if (profileDTO != null) {
            profile.setId(profileDTO.getId());

            Set<SkillProfile> skills = new HashSet<>();
            for (Map.Entry<Integer, Integer> entry : profileDTO.getSkillIds().entrySet()) {
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
     *
     * @param profile profile to be mapped
     * @return a profile dto
     * @author Miruna
     */
    public ProfileDTO getDTOFromProfile(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();

        if (profile != null) {
            profileDTO.setId(profile.getId());

            Map<Integer, Integer> skills = new HashMap<>();
            for (SkillProfile skillProfile : profile.getSkillProfiles()) {
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

    public ConsultingLevelDTO getDTOFromConsultingLevel(ConsultingLevel consultingLevel) {
        ConsultingLevelDTO consultingLevelDTO = new ConsultingLevelDTO();

        if (consultingLevel != null) {
            consultingLevelDTO.setId(consultingLevel.getId());
            consultingLevelDTO.setName(consultingLevel.getName());
            consultingLevelDTO.setDescription(consultingLevel.getDescription());
            consultingLevelDTO.setStatus(consultingLevel.getStatus());
        }

        return consultingLevelDTO;
    }

    public ConsultingLevel getConsultingLevelFromDTO(ConsultingLevelDTO consultingLevelDTO) {
        ConsultingLevel consultingLevel = new ConsultingLevel();

        if (consultingLevelDTO != null) {
            consultingLevel.setId(consultingLevelDTO.getId());
            consultingLevel.setName(consultingLevelDTO.getName());
            consultingLevel.setDescription(consultingLevelDTO.getDescription());
            consultingLevel.setStatus(consultingLevelDTO.getStatus());
        }

        return consultingLevel;
    }

    public RegionDTO getDTOFromRegion(Region region) {
        RegionDTO regionDTO = new RegionDTO();

        if (region != null) {
            regionDTO.setId(region.getId());
            regionDTO.setRegionName(region.getRegionName());
            regionDTO.setStatus(region.getStatus());
        }

        return regionDTO;
    }


    public Region getRegionFromDTO(RegionDTO regionDTO) {
        Region region = new Region();

        if (regionDTO != null) {
            region.setId(regionDTO.getId());
            region.setRegionName(regionDTO.getRegionName());
            region.setStatus(regionDTO.getStatus());
        }

        return region;
    }

    public SkillDTO getDTOFromSkill(Skill skill) {
        SkillDTO skillDTO = new SkillDTO();

        if (skill != null) {
            skillDTO.setId(skill.getId());
            skillDTO.setName(skill.getName());
            skillDTO.setStatus(skill.getStatus());
            skillDTO.setSkillAreaId(skill.getSkillArea().getId());
        }

        return skillDTO;
    }

    public Skill getSkillFromDto(SkillDTO skillDTO) {
        Skill skill = new Skill();

        if (skillDTO != null) {
            skill.setId(skillDTO.getId());
            skill.setName(skillDTO.getName());
            skill.setStatus(skillDTO.getStatus());
            skillAreaRepository.findById(skillDTO.getSkillAreaId()).ifPresent(skill::setSkillArea);
        }

        return skill;
    }

    public SkillAreaDTO getDTOFromSkillArea(SkillArea skillArea) {
        SkillAreaDTO skillAreaDTO = new SkillAreaDTO();

        if (skillArea != null) {
            skillAreaDTO.setId(skillArea.getId());
            skillAreaDTO.setName(skillArea.getName());
            skillAreaDTO.setStatus(skillArea.getStatus());
        }

        return skillAreaDTO;
    }

    public SkillArea getSkillAreaFromDTO(SkillAreaDTO skillAreaDTO) {
        SkillArea skillArea = new SkillArea();

        if (skillAreaDTO != null) {
            skillArea.setId(skillAreaDTO.getId());
            skillArea.setName(skillAreaDTO.getName());
            skillArea.setStatus(skillAreaDTO.getStatus());
        }

        return skillArea;
    }

    public Project dtoToProject(ProjectDto projectDto) {
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

    public ProjectDto projectToDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setDescription(project.getDescription());
        projectDto.setDuration(project.getDuration());
        projectDto.setStatus(project.getStatus());
        projectDto.setCustomerId(project.getCustomer().getId());
        projectDto.setIndustryId(project.getIndustry().getId());
        return projectDto;
    }

    public Customer dtoToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setStatus(customerDto.getStatus());
        return customer;
    }

    public CustomerDto customerToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setStatus(customer.getStatus());
        return customerDto;
    }

    public Industry dtoToIndustry(IndustryDto industryDto) {
        Industry industry = new Industry();
        industry.setId(industryDto.getId());
        industry.setName(industryDto.getName());
        industry.setDescription(industryDto.getDescription());
        industry.setStatus(industryDto.getStatus());
        return industry;
    }

    public IndustryDto industryToDto(Industry industry) {
        IndustryDto industryDto = new IndustryDto();
        industryDto.setId(industry.getId());
        industryDto.setName(industry.getName());
        industryDto.setDescription(industry.getDescription());
        industryDto.setStatus(industry.getStatus());
        return industryDto;
    }

    public UserDTO  userToDTO(User user, Boolean setSupervisor) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setAdmin(user.getAdmin());
        userDTO.setStatus(user.getStatus());

        if (setSupervisor && user.getSupervisor() != null) {
            userDTO.setSupervisor(userToDTO(user.getSupervisor(), false));
        }

        userDTO.setProjects(user.getProjects().stream()
                .map(project -> projectToDto(project))
                .collect(Collectors.toList()));

        userDTO.setSupervising(user.getSupervising().stream()
                .map(u -> userToDTO(u, false))
                .collect(Collectors.toSet()));

        return userDTO;
    }

    public UserDTO  userToDTO(User user) {
        return userToDTO(user, true);
    }

    public User dtoToUser(UserDTO userDTO, Boolean setSupervisor) {
        User user = new User();

        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setAdmin(userDTO.getAdmin());
        user.setStatus(userDTO.getStatus());

        if (setSupervisor) {
            user.setSupervisor(dtoToUser(userDTO.getSupervisor(), false));
        }

        user.setProjects(userDTO.getProjects().stream()
                .map(project -> dtoToProject(project))
                .collect(Collectors.toList()));

        user.setSupervising(userDTO.getSupervising().stream()
                .map(u -> dtoToUser(u, false))
                .collect(Collectors.toSet()));

        return user;
    }

    public User dtoToUser(UserDTO userDTO) {
        return dtoToUser(userDTO, true);
    }
}
