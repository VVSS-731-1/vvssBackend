package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.ProfileDTO;
import com.example.demo.service.dto.ProjectDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Test class for ProfileService.
 *
 * @author Miruna Dinu
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ProfileServiceTest {

    @InjectMocks
    ProfileService profileService;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private  RegionRepository regionRepository;

    @Mock
    private  ConsultingLevelRepository consultingLevelRepository;

    @Mock
    private  UserRepository userRepository;

    @Mock
    private  SkillRepository skillRepository;

    @Mock
    private  SkillAreaRepository skillAreaRepository;

    @Mock
    private  IndustryRepository industryRepository;

    @Mock
    private CustomerRepository customerRepository;

    private Profile profile;

    @Test
    void saveProfile() {
    }

    @Test
    void updateProfile_returnsProfileWithChanges_withNotNullProfile() {
        Region region = getRegion();
        region.setRegionName("New region");
        region.setId(8);
        
        ConsultingLevel consultingLevel = getConsultingLevel();
        consultingLevel.setId(7);
        consultingLevel.setDescription("New consulting level");
        consultingLevel.setName("ConsultingLevel2");
        
        profile = getProfile();
        profile.setRegion(region);
        profile.setConsultingLevel(consultingLevel);
        ProfileDTO profileDTONoChanges = DtoMapping.getDTOFromProfile(getProfile());
        ProfileDTO profileDTOWithChanges = DtoMapping.getDTOFromProfile(profile);

        when(profileRepository.getOne(profileDTOWithChanges.getId())).thenReturn(profile);
        when(regionRepository.getOne(profileDTOWithChanges.getRegionId())).thenReturn(region);
        when(consultingLevelRepository.getOne(profileDTOWithChanges.getConsultingLevelId())).thenReturn(consultingLevel);
        when(userRepository.getOne(profileDTOWithChanges.getUserId())).thenReturn(getUser());

        List<Integer> skillIds = new ArrayList<>(Collections.singletonList(1));
        Map<Integer, Integer> skillMap = new HashMap<>();
        skillMap.put(1, 5);
        when(skillRepository.getOne(skillIds.get(0))).thenReturn(getSkill());
        profileDTOWithChanges.setSkillIds(skillMap);
        ProfileDTO profileTest = profileService.updateProfile(profileDTOWithChanges);

        Assert.assertNotEquals(profileDTONoChanges, profileTest);
    }

    @Test
    void deactivateProfile_returnsProfileWithChangedStatus_withNotNullProfile() {
        ProfileDTO profileDTO = DtoMapping.getDTOFromProfile(getProfile());
        profile = getProfile();

        when(profileRepository.save(profile)).thenReturn(profile);
        when(profileRepository.getOne(profileDTO.getId())).thenReturn(profile);

        ProfileDTO profileTest = profileService.deleteProfile(profileDTO);

        Assert.assertNotEquals(profileDTO, profileTest);
        Assert.assertFalse(profileTest.getStatus());
        Assert.assertTrue(profileDTO.getStatus());
    }

    @Test
    void deactivateProfile_returnsNull_withNullProfile() {
        ProfileDTO profileDTO = DtoMapping.getDTOFromProfile(getProfile());

        when(profileRepository.getOne(profileDTO.getId())).thenReturn(null);

        ProfileDTO profileTest = profileService.deleteProfile(profileDTO);

        Assert.assertNull(profileTest);
        Assert.assertTrue(profileDTO.getStatus());
    }

    private Profile getProfile() {
        profile = new Profile();

        profile.setId(1);
        profile.setSkillProfiles(new HashSet<>(Collections.singleton(new SkillProfile(getSkill(), profile, 5))));
        profile.setRegion(getRegion());
        profile.setConsultingLevel(getConsultingLevel());
        profile.setUser(getUser());
        profile.setStatus(true);
        profile.setImage(new byte[4]);

        return profile;
    }

    private Region getRegion() {
        Region region = new Region();

        region.setId(1);
        region.setRegionName("Region1");
        region.setStatus(true);

        return region;
    }

    private ConsultingLevel getConsultingLevel() {
        ConsultingLevel consultingLevel = new ConsultingLevel();
        consultingLevel.setId(1);
        consultingLevel.setName("ConsultingLevel1");
        consultingLevel.setDescription("ConsultingLevel1");
        consultingLevel.setStatus(true);

        return consultingLevel;
    }

    private User getUser() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Miruna");
        user.setLastName("Dinu");
        user.setUsername("miruna.dinu");
        user.setEmail("miruna.dinu@mail.com");
        user.setAdmin(true);
        user.setProjects(new ArrayList<Project>(Collections.singleton(new Project(1, "Project1", "Project1", true, 10L, getIndustry(), getCustomer()))));
        user.setStatus(true);
        user.setSupervisor(new User());
        user.setSupervising(new HashSet<>());
        return user;
    }

    private Industry getIndustry() {
        Industry industry = new Industry();

        industry.setId(1);
        industry.setName("Industry1");
        industry.setDescription("Industry1");
        industry.setStatus(true);

        return industry;
    }

    private Customer getCustomer() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Ana");
        customer.setStatus(true);

        return customer;
    }

    private Skill getSkill() {
        Skill skill = new Skill();

        skill.setId(1);
        skill.setName("Skill2");
        skill.setStatus(true);
        skill.setSkillArea(getSkillArea());

        return skill;
    }

    private SkillArea getSkillArea() {
        SkillArea skillArea = new SkillArea();

        skillArea.setId(1);
        skillArea.setName("SkillArea1");
        skillArea.setStatus(true);

        return skillArea;
    }
}