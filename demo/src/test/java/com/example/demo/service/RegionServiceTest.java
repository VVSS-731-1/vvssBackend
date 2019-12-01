package com.example.demo.service;

import com.example.demo.DemoApplication;
import com.example.demo.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import org.junit.runner.RunWith;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegionServiceTest {

    @Autowired
    RegionService regionService;

    @org.junit.Test
    @Transactional
    public void getAllRegions() {

        Region region = new Region(){{
            setRegionName("Cluj-Napoca");
            setStatus(false);
        }};

        regionService.save(region);

        List<Region> regions = Arrays.asList(new Region() {{
            setId(1);
            setRegionName("Sibiu");
            setStatus(false);
        }});

        List<Region> regionList = regionService.getAllRegions();

        assertThat(regionList.size())
                .isEqualTo(2);

        assertThat(regionList.get(0))
                .isEqualToIgnoringGivenFields(regions.get(0));

    }
}