package com.example.demo.service;

import com.example.demo.model.ConsultingLevel;
import com.example.demo.service.services.ConsultingLevelService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ConsultingLevelServiceTest {

    @Autowired
    ConsultingLevelService consultingLevelService;

    @Test
    void findAll() {

        List<ConsultingLevel> consultingLevelServices = consultingLevelService.findAll();

        assertEquals(consultingLevelServices.size(), 6);

    }
}