package com.example.demo.service;

import com.example.demo.model.Region;
import com.example.demo.repository.RegionRepository;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.RegionDTO;
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

    @Test
    void save_returnsRegion_withNotNullRegion() {
        RegionDTO regionDTO = DtoMapping.getDTOFromRegion(getRegion());
        region = getRegion();

        when(regionRepository.save(region)).thenReturn(region);

        RegionDTO regionTest = regionService.save(regionDTO);
        Assert.assertEquals(regionDTO, regionTest);
    }

    @Test
    void deactivateRegion_returnsRegionWithChangedStatus_withNotNullRegion() {
        RegionDTO regionDTO = DtoMapping.getDTOFromRegion(getRegion());
        region = getRegion();

        when(regionRepository.save(region)).thenReturn(region);
        when(regionRepository.getOne(regionDTO.getId())).thenReturn(region);

        RegionDTO regionTest = regionService.deactivateRegion(regionDTO);

        Assert.assertNotEquals(regionDTO, regionTest);
        Assert.assertFalse(regionTest.getStatus());
        Assert.assertTrue(regionDTO.getStatus());
    }

    @Test
    void deactivateRegion_returnsNull_withNullRegion() {
        RegionDTO regionDTO = DtoMapping.getDTOFromRegion(getRegion());

        when(regionRepository.getOne(regionDTO.getId())).thenReturn(null);

        RegionDTO regionTest = regionService.deactivateRegion(regionDTO);

        Assert.assertNull(regionTest);
        Assert.assertTrue(regionDTO.getStatus());
    }

    private Region getRegion() {
        region = new Region();

        region.setId(1);
        region.setRegionName("Region1");
        region.setStatus(true);

        return region;
    }
}