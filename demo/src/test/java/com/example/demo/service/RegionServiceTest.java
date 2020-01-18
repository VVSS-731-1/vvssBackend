package com.example.demo.service;

import com.example.demo.model.Region;
import com.example.demo.service.dto.DtoMapping;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegionServiceTest {


    @Autowired
    RegionService regionService;

    @Autowired
    private DtoMapping dtoMapping;

    @Test
    public void getAllRegions() {

        Region region = new Region();
        region.setRegionName("Iasi");
        region.setStatus(false);


        regionService.save(dtoMapping.getDTOFromRegion(region));

        List<Region> regions = Arrays.asList(new Region() {{
            setId(1);
            setRegionName("Cluj");
            setStatus(false);
        }});

        List<Region> regionList = regionService.getAllRegions();

        assertThat(regionList.size())
                .isEqualTo(7);

        assertThat(regionList.get(0))
                .isEqualToIgnoringGivenFields(regions.get(0));

    }
}