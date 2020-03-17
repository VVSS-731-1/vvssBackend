package com.example.demo.service;

import com.example.demo.model.Region;
import com.example.demo.repository.RegionRepository;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.services.RegionService;
import org.hibernate.LazyInitializationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegionServiceTest {

    @Autowired
    RegionService regionService;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    private DtoMapping dtoMapping;

    @Test
    public void getAllRegions() {
        int initialNumberOfRegions = regionRepository.findAll().size();

        Region region = new Region();
        region.setName("Iasi");
        region.setStatus(false);


        regionService.save(dtoMapping.getDTOFromRegion(region));

        List<Region> regions = Collections.singletonList(new Region() {{
            setId(1);
            setName("Cluj");
            setStatus(false);
        }});

        List<Region> regionList = regionService.getAllRegions();

        assertThat(regionList.size())
                .isEqualTo(initialNumberOfRegions + 1);

        assertThat(regionList.get(0))
                .isEqualToIgnoringGivenFields(regions.get(0));
    }

    @Test
    public void findById() {
        List<Region> regionList = regionService.getAllRegions();

        if (regionList.size() != 0) {
            Region existingRegion = regionList.get(0);

            assertThat(dtoMapping.getRegionFromDTO(regionService.findById(existingRegion.getId())))
                    .isEqualToIgnoringGivenFields(existingRegion);
        }

        assertThat(regionService.findById(-1))
                .isNull();
    }

    @Test
    public void save() {
        int initialNumberOfRegions = regionRepository.findAll().size();

        Region region = new Region();
        region.setName("Iasi");
        region.setStatus(false);


        regionService.save(dtoMapping.getDTOFromRegion(region));

        List<Region> regionList = regionService.getAllRegions();
        Region maxIdRegion = regionList.stream().max(Comparator.comparing(Region::getId)).orElse(null);
        assertThat(maxIdRegion).isNotNull();
        assertThat(maxIdRegion.getId()).isGreaterThan(0);
        region.setId(maxIdRegion.getId());

        assertThat(regionList.size())
                .isEqualTo(initialNumberOfRegions + 1);

        assertThat(maxIdRegion)
                .isEqualToIgnoringGivenFields(region);
    }

    @Test(expected = LazyInitializationException.class)
    public void deactivateRegion() {
        Region region = new Region() {{
            setName("Iasi");
            setStatus(true);
        }};
        regionService.save(dtoMapping.getDTOFromRegion(region));

        List<Region> regionList = regionService.getAllRegions();
        Region maxIdRegion = regionList.stream().max(Comparator.comparing(Region::getId)).orElse(null);
        assertThat(maxIdRegion).isNotNull();
        assertThat(maxIdRegion.getId()).isGreaterThan(0);
        region.setId(maxIdRegion.getId());

        assertThat(dtoMapping.getRegionFromDTO(regionService.deactivateRegion(dtoMapping.getDTOFromRegion(region))).getStatus())
                .isFalse();

        Region invalidIdRegion = new Region() {{
            setId(-1);
            setName("Cluj");
            setStatus(false);
        }};

        regionService.deactivateRegion(dtoMapping.getDTOFromRegion(invalidIdRegion));
    }

    @Test
    public void update() {
        Region region = new Region() {{
            setId(1);
            setName("Cluj");
            setStatus(true);
        }};
        regionService.regionUpdate(dtoMapping.getDTOFromRegion(region));

        List<Region> regionList = regionService.getAllRegions();

        assertThat(regionList.get(0))
                .isEqualToIgnoringGivenFields(region);

        Region invalidRegion = new Region() {{
            setId(-1);
            setName("Cluj");
            setStatus(true);
        }};
        assertThat(regionService.regionUpdate(dtoMapping.getDTOFromRegion(invalidRegion)))
                .isNull();

        // restoring database prior state
        region.setStatus(false);
        regionService.regionUpdate(dtoMapping.getDTOFromRegion(region));
    }
}
