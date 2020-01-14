package com.example.demo.service;

import com.example.demo.model.SkillArea;
import com.example.demo.repository.SkillAreaRepository;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.SkillAreaDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Test class for SkillAreaService.
 *
 * @author Miruna Dinu
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class SkillAreaServiceTest {

    @InjectMocks
    SkillAreaService skillAreaService;

    @Mock
    private SkillAreaRepository skillAreaRepository;

    private SkillArea skillArea;

    @Test
    void save_returnsSkillArea_withNotNullSkillArea() {
        SkillAreaDTO skillAreaDTO = DtoMapping.getDTOFromSkillArea(getSkillArea());
        skillArea = getSkillArea();

        when(skillAreaRepository.save(skillArea)).thenReturn(skillArea);

        SkillAreaDTO skillAreaTest = skillAreaService.save(skillAreaDTO);

        Assert.assertEquals(skillAreaDTO, skillAreaTest);
    }

    @Test
    void deactivateSkillArea_returnsSkillAreaWithChangedStatus_withNotNullSkillArea() {
        SkillAreaDTO skillAreaDTO = DtoMapping.getDTOFromSkillArea(getSkillArea());
        skillArea = getSkillArea();

        when(skillAreaRepository.save(skillArea)).thenReturn(skillArea);
        when(skillAreaRepository.getOne(skillAreaDTO.getId())).thenReturn(skillArea);

        SkillAreaDTO skillAreaTest = skillAreaService.deactivateSkillArea(skillAreaDTO);

        Assert.assertNotEquals(skillAreaDTO, skillAreaTest);
        Assert.assertFalse(skillAreaTest.getStatus());
        Assert.assertTrue(skillAreaDTO.getStatus());
    }

    @Test
    void deactivateSkillArea_returnsNull_withNullSkillArea() {
        SkillAreaDTO skillAreaDTO = DtoMapping.getDTOFromSkillArea(getSkillArea());

        when(skillAreaRepository.getOne(skillAreaDTO.getId())).thenReturn(null);

        SkillAreaDTO skillAreaTest = skillAreaService.deactivateSkillArea(skillAreaDTO);

        Assert.assertNull(skillAreaTest);
        Assert.assertTrue(skillAreaDTO.getStatus());
    }

    private SkillArea getSkillArea() {
        skillArea = new SkillArea();

        skillArea.setId(1);
        skillArea.setName("SkillArea1");
        skillArea.setStatus(true);

        return skillArea;
    }
}