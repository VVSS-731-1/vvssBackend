package com.example.demo.service;

import com.example.demo.model.Skill;
import com.example.demo.model.SkillArea;
import com.example.demo.repository.SkillAreaRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.SkillAreaDTO;
import com.example.demo.service.dto.SkillDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Test class for SkillService.
 *
 * @author Miruna Dinu
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class SkillServiceTest {

    @InjectMocks
    SkillService skillService;

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private SkillAreaRepository skillAreaRepository;

    private Skill skill;

//    @Test
//    void save_returnsSkillArea_withNotNullSkillArea() {
//        SkillDTO skillDTO = DtoMapping.getDTOFromSkill(getSkill());
//        skill = getSkill();
//
//        when(skillRepository.save(skill)).thenReturn(skill);
//        when(skillAreaRepository.getOne(skill.getSkillArea().getId())).thenReturn(getSkillArea());
////        when(skillAreaService.getSkillAreaById(getSkillArea().getId())).thenReturn(DtoMapping.getDTOFromSkillArea(getSkillArea()));
////        when(skillAreaRepository.findById(getSkillArea().getId())).thenReturn(java.util.Optional.ofNullable(getSkillArea()));
//        SkillDTO skillTest  = skillService.save(skillDTO);
//
//        Assert.assertEquals(skillDTO, skillTest);
//    }

    @Test
    void deactivateSkill_returnsSkillWithChangedStatus_withNotNullSkill() {
        SkillDTO skillDTO = DtoMapping.getDTOFromSkill(getSkill());
        skill = getSkill();

        when(skillRepository.save(skill)).thenReturn(skill);
        when(skillRepository.getOne(skillDTO.getId())).thenReturn(skill);

        SkillDTO skillTest = skillService.deactivateSkill(skillDTO);

        Assert.assertNotEquals(skillDTO, skillTest);
        Assert.assertFalse(skillTest.getStatus());
        Assert.assertTrue(skillDTO.getStatus());
    }

    @Test
    void deactivateSkill_returnsNull_withNullSkill() {
        SkillDTO skillDTO = DtoMapping.getDTOFromSkill(getSkill());

        when(skillRepository.getOne(skillDTO.getId())).thenReturn(null);

        SkillDTO skillTest = skillService.deactivateSkill(skillDTO);

        Assert.assertNull(skillTest);
        Assert.assertTrue(skillDTO.getStatus());
    }

    private Skill getSkill() {
        skill = new Skill();

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