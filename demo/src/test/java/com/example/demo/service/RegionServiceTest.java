package com.example.demo.service;

import com.example.demo.DemoApplication;
import com.example.demo.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class RegionServiceTest {

    @Autowired
    RegionService regionService;

    @org.junit.Test
    public void getAllRegions() {

        List<Region> regions = Arrays.asList(new Region() {{
            setId(1);
            setRegionName("Cluj");
        }}, new Region() {{
            setId(2);
            setRegionName("Mures");
        }}, new Region() {{
            setId(3);
            setRegionName("Timisoara");
        }}, new Region() {{
            setId(4);
            setRegionName("Oradea");
        }}, new Region() {{
            setId(5);
            setRegionName("Bistrita");
        }}, new Region() {{
            setId(6);
            setRegionName("Sibiu");
        }});

        List<Region> regionList = regionService.getAllRegions();

        assertThat(regionList.size())
                .isEqualTo(6);

        assertThat(regionList.get(0))
                .isEqualToIgnoringGivenFields(regions.get(0));

    }
}