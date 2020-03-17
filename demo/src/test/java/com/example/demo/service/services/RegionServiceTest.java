package com.example.demo.service.services;

import com.example.demo.model.Region;
import com.example.demo.repository.RegionRepository;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.RegionDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Test class for RegionService.
 *
 * @author Miruna Dinu
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class RegionServiceTest {

    @InjectMocks
    RegionService regionService;

    @Mock
    private RegionRepository regionRepository;

    private Region region;

    private RegionDTO regionDTO;

    @Mock
    private DtoMapping dtoMapping;

    @Test
    void save_returnsRegion_withNotNullRegion() {
        regionDTO = getRegionDto();
        region = getRegion();

        when(dtoMapping.getRegionFromDTO(regionDTO)).thenReturn(region);
        when(regionRepository.save(region)).thenReturn(region);
        when(dtoMapping.getDTOFromRegion(region)).thenReturn(regionDTO);

        RegionDTO regionTest = regionService.save(regionDTO);
        Assert.assertEquals(regionDTO, regionTest);
    }

    @Test
    void deactivateRegion_returnsRegionWithChangedStatus_withNotNullRegion() {
        regionDTO = getRegionDto();
        region = getRegion();

        when(regionRepository.save(region)).thenReturn(region);
        when(regionRepository.getOne(regionDTO.getId())).thenReturn(region);

        RegionDTO regionTest = regionService.deactivateRegion(regionDTO);

        Assert.assertEquals(regionDTO, regionTest);
        Assert.assertFalse(regionTest.getStatus());
        Assert.assertFalse(regionDTO.getStatus());
    }

    @Test
    void deactivateRegion_returnsNull_withNullRegion() {
        regionDTO = getRegionDto();

        when(regionRepository.getOne(regionDTO.getId())).thenReturn(null);

        RegionDTO regionTest = regionService.deactivateRegion(regionDTO);

        Assert.assertNull(regionTest);
        Assert.assertTrue(regionDTO.getStatus());
    }

    private Region getRegion() {
        region = new Region();

        region.setId(1);
        region.setName("Region1");
        region.setStatus(true);

        return region;
    }

    private RegionDTO getRegionDto() {
        regionDTO = new RegionDTO();

        regionDTO.setId(1);
        regionDTO.setName("Region1");
        regionDTO.setStatus(true);

        return regionDTO;
    }
}
