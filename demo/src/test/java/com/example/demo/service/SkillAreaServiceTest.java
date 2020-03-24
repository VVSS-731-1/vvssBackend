package com.example.demo.service;

import com.example.demo.model.SkillArea;
import com.example.demo.repository.SkillAreaRepository;
import com.example.demo.service.services.SkillAreaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class SkillAreaServiceTest {

    @Autowired
    SkillAreaService service;

    @MockBean
    SkillAreaRepository repository;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(Stream.of(new SkillArea(), new SkillArea()).collect(Collectors.toList()));
        assertEquals(2, service.findAll().size());
    }

    @Test
    void findById() {
        int id = 82;
        SkillArea skillArea = new SkillArea();
        skillArea.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(skillArea));
        assertEquals(id, service.findById(id).getId());

        when(repository.findById(-1)).thenReturn(null);
        assertNull(service.findById(-1));
    }
}