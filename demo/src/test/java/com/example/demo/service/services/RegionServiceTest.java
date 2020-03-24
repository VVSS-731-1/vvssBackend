package com.example.demo.service.services;

import com.example.demo.model.Region;
import com.example.demo.repository.RegionRepository;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.ProjectDto;
import com.example.demo.service.dto.RegionDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
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
    void getAllRegions_returnsList_whenCalled() {
        when(regionRepository.findAll()).thenReturn(Stream.of(new Region(), new Region()).collect(Collectors.toList()));
        Assert.assertEquals(2, regionService.getAllRegions().size());
    }

    @Test
    void findById_returnsRegion_whenIdIsGiven() {
        region = getRegion();
        regionDTO = getRegionDto();

        when(regionRepository.findById(region.getId())).thenReturn(Optional.of(region));
        when(dtoMapping.getDTOFromRegion(any())).thenReturn(regionDTO);

        RegionDTO result = regionService.findById(region.getId());
        Assert.assertEquals(regionDTO, result);
    }

    @Test
    void findById_returnsNull_whenIdIsNull() {
        RegionDTO result = regionService.findById(null);
        Assert.assertNull(result);
    }


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

    @Test
    void regionUpdate_returnsUpdatedRegion_whenRegionIsNotNull() {
        regionDTO = getRegionDto();
        regionDTO.setName("Region2");
        region = getRegion();

        when(dtoMapping.getRegionFromDTO(any())).thenReturn(region);
        when(regionRepository.findById(region.getId())).thenReturn(Optional.of(region));
        when(dtoMapping.getDTOFromRegion(any())).thenReturn(regionDTO);

        RegionDTO result = regionService.regionUpdate(getRegionDto());
        Assert.assertNotEquals(result, regionDTO);
    }

    @Test
    void regionUpdate_returnsNull_whenRegionIsNull() {
        regionDTO = getRegionDto();
        regionDTO.setId(null);
        RegionDTO result = regionService.regionUpdate(regionDTO);
        Assert.assertNotEquals(result, regionDTO);
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
