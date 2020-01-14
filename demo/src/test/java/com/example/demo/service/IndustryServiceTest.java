package com.example.demo.service;

import com.example.demo.model.Industry;
import com.example.demo.repository.IndustryRepository;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.IndustryDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Test class for IndustryService.
 *
 * @author Miruna Dinu
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class IndustryServiceTest {

    @InjectMocks
    IndustryService industryService;

    @Mock
    private IndustryRepository industryRepository;

    private Industry industry;

    @Test
    void save_returnsIndustry_withNotNullIndustry() {
        IndustryDTO industryDTO = DtoMapping.industryToDto(getIndustry());
        industry = getIndustry();

        when(industryRepository.save(industry)).thenReturn(industry);

        IndustryDTO industryTest = industryService.save(industryDTO);

        Assert.assertEquals(industryDTO, industryTest);
    }

    @Test
    void deactivateIndustry_returnsIndustryWithChangedStatus_withNotNullIndustry() {
        IndustryDTO industryDTO = DtoMapping.industryToDto(getIndustry());
        industry = getIndustry();

        when(industryRepository.save(industry)).thenReturn(industry);
        when(industryRepository.findById(industryDTO.getId())).thenReturn(java.util.Optional.ofNullable(industry));

        IndustryDTO industryTest = industryService.deactivateIndustry(industryDTO);

        Assert.assertEquals(industryDTO, industryTest);
        Assert.assertFalse(industryDTO.getStatus());
    }

    @Test
    void deactivateIndustry_noChanges_withNotExistingIndustry() {
        IndustryDTO industryDTO = DtoMapping.industryToDto(getIndustry());
        industry = getIndustry();

        when(industryRepository.findById(industryDTO.getId())).thenReturn(Optional.empty());

        IndustryDTO industryTest = industryService.deactivateIndustry(industryDTO);

        Assert.assertEquals(industryDTO, industryTest);
        Assert.assertTrue(industryDTO.getStatus());
    }

    private Industry getIndustry() {
        industry = new Industry();

        industry.setId(1);
        industry.setName("Industry1");
        industry.setDescription("Industry1");
        industry.setStatus(true);

        return industry;
    }
}