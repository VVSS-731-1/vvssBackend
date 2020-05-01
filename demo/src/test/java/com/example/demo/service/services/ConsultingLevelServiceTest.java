package com.example.demo.service.services;

import com.example.demo.model.ConsultingLevel;
import com.example.demo.repository.ConsultingLevelRepository;
import com.example.demo.service.dto.ConsultingLevelDTO;
import com.example.demo.service.dto.DtoMapping;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ConsultingLevelServiceTest {

    @InjectMocks
    private ConsultingLevelService consultingLevelService;

    @Mock
    private ConsultingLevelRepository consultingLevelRepository;

    @Mock
    private DtoMapping dtoMapping;

    private ConsultingLevel consultingLevel;
    private ConsultingLevelDTO consultingLevelDTO;

    @Test
    void findAll() {
        when(consultingLevelRepository.findAll()).thenReturn(getListCL());

        Assert.assertEquals(getListCL(), this.consultingLevelService.findAll());
    }

    @Test
    void findById() {
        consultingLevel = getOneCL();
        consultingLevelDTO = getOneCLDTO();

        when(consultingLevelRepository.findById(1)).thenReturn(Optional.of(consultingLevel));
        when(dtoMapping.getDTOFromConsultingLevel(any())).thenReturn(consultingLevelDTO);

        ConsultingLevelDTO returnedCL = this.consultingLevelService.findById(1);
        Assert.assertEquals(returnedCL.getId(), getOneCLDTO().getId());
        Assert.assertEquals(returnedCL.getName(), getOneCLDTO().getName());
        Assert.assertEquals(returnedCL.getDescription(), getOneCLDTO().getDescription());
        Assert.assertEquals(returnedCL.getStatus(), getOneCLDTO().getStatus());
    }

    @Test
    void findByInvalidId() {
        consultingLevel = getOneCL();
        consultingLevelDTO = getOneCLDTO();

        when(consultingLevelRepository.findById(1)).thenReturn(Optional.of(new ConsultingLevel()));

        ConsultingLevelDTO returnedCL = this.consultingLevelService.findById(1);
        Assert.assertNull(returnedCL);
    }

    @Test
    void save() {
        consultingLevel = getOneCL();
        consultingLevelDTO = getOneCLDTO();

        when(dtoMapping.getConsultingLevelFromDTO(consultingLevelDTO)).thenReturn(consultingLevel);
        when(consultingLevelRepository.save(consultingLevel)).thenReturn(consultingLevel);
        when(dtoMapping.getDTOFromConsultingLevel(consultingLevel)).thenReturn(consultingLevelDTO);

        ConsultingLevelDTO returnedCL = this.consultingLevelService.save(consultingLevelDTO);
        Assert.assertEquals(returnedCL.getId(), getOneCLDTO().getId());
        Assert.assertEquals(returnedCL.getName(), getOneCLDTO().getName());
        Assert.assertEquals(returnedCL.getDescription(), getOneCLDTO().getDescription());
        Assert.assertEquals(returnedCL.getStatus(), getOneCLDTO().getStatus());
    }

    @Test
    void deactivateConsultingLevel() {
        consultingLevel = getOneCL();
        consultingLevelDTO = getOneCLDTO();

        when(consultingLevelRepository.getOne(1)).thenReturn(getOneCL());
        when(consultingLevelRepository.save(consultingLevel)).thenReturn(consultingLevel);

        ConsultingLevel returnedCL = this.consultingLevelService.deactivateConsultingLevel(consultingLevelDTO);
        consultingLevel.setStatus(false);
        Assert.assertEquals(consultingLevel, returnedCL);
        Assert.assertFalse(returnedCL.getStatus());
        Assert.assertNotEquals(returnedCL.getStatus(), true);
    }

    private List<ConsultingLevel> getListCL() {
        ConsultingLevel cl1 = new ConsultingLevel();
        cl1.setId(1);
        cl1.setName("Consulting Level");
        cl1.setDescription("Consulting this and that.");
        cl1.setStatus(false);

        ConsultingLevel cl2 = new ConsultingLevel();
        cl2.setId(2);
        cl2.setName("Consulting Level");
        cl2.setDescription("Consulting this and that.");
        cl2.setStatus(false);

        List<ConsultingLevel> list = new ArrayList<>();
        list.add(cl1);
        list.add(cl2);

        return list;
    }

    private ConsultingLevel getOneCL() {
        ConsultingLevel cl1 = new ConsultingLevel();
        cl1.setId(1);
        cl1.setName("Consulting Level");
        cl1.setDescription("Consulting this and that.");
        cl1.setStatus(false);

        return cl1;
    }

    private ConsultingLevelDTO getOneCLDTO(){
        ConsultingLevelDTO cl1 = new ConsultingLevelDTO();
        cl1.setId(1);
        cl1.setName("Consulting Level");
        cl1.setDescription("Consulting this and that.");
        cl1.setStatus(false);

        return cl1;
    }
}