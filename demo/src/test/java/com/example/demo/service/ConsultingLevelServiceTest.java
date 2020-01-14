package com.example.demo.service;

import com.example.demo.model.ConsultingLevel;
import com.example.demo.repository.ConsultingLevelRepository;
import com.example.demo.service.dto.ConsultingLevelDTO;
import com.example.demo.service.dto.DtoMapping;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Test class for ConsultingLevelService.
 *
 * @author Miruna Dinu
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ConsultingLevelServiceTest {

    @InjectMocks
    ConsultingLevelService consultingLevelService;

    @Mock
    private ConsultingLevelRepository consultingLevelRepository;

    private ConsultingLevel consultingLevel;

    @Test
    void save_returnsConsultingLevel_withNotNullConsultingLevel() {
        ConsultingLevelDTO consultingLevelDTO = DtoMapping.getDTOFromConsultingLevel(getConsultingLevel());
        consultingLevel = getConsultingLevel();

        when(consultingLevelRepository.save(any())).thenReturn(consultingLevel);

        ConsultingLevelDTO consultingLevelTest = consultingLevelService.save(consultingLevelDTO);

        Assert.assertEquals(consultingLevelDTO, consultingLevelTest);
    }

    @Test
    void deactivateConsultingLevel_returnsConsultingLevelWithChangedStatus_withNotNullConsultingLevel() {

        ConsultingLevelDTO consultingLevelDTO = DtoMapping.getDTOFromConsultingLevel(getConsultingLevel());
        consultingLevel = getConsultingLevel();

        when(consultingLevelRepository.save(any())).thenReturn(consultingLevel);
        when(consultingLevelRepository.getOne(consultingLevelDTO.getId())).thenReturn(consultingLevel);

        ConsultingLevelDTO consultingLevelTest = consultingLevelService.deactivateConsultingLevel(consultingLevelDTO);

        Assert.assertNotEquals(consultingLevelDTO, consultingLevelTest);
        Assert.assertFalse(consultingLevelTest.getStatus());
        Assert.assertTrue(consultingLevelDTO.getStatus());
    }

    @Test
    void deactivateConsultingLevel_returnsNull_withNullConsultingLevel() {

        ConsultingLevelDTO consultingLevelDTO = DtoMapping.getDTOFromConsultingLevel(getConsultingLevel());

        when(consultingLevelRepository.getOne(consultingLevelDTO.getId())).thenReturn(null);

        ConsultingLevelDTO consultingLevelTest = consultingLevelService.deactivateConsultingLevel(consultingLevelDTO);

        Assert.assertNull(consultingLevelTest);
        Assert.assertTrue(consultingLevelDTO.getStatus());
    }

    private ConsultingLevel getConsultingLevel() {
        consultingLevel = new ConsultingLevel();
        consultingLevel.setId(1);
        consultingLevel.setName("ConsultingLevel1");
        consultingLevel.setDescription("ConsultingLevel1");
        consultingLevel.setStatus(true);

        return consultingLevel;
    }
}