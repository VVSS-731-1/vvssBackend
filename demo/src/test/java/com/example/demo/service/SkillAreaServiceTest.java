package com.example.demo.service;

import com.example.demo.model.SkillArea;
import com.example.demo.repository.SkillAreaRepository;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.SkillAreaDTO;
import com.example.demo.service.services.SkillAreaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class SkillAreaServiceTest {

    @InjectMocks
    SkillAreaService service;

    @Mock
    SkillAreaRepository repository;

    @Mock
    private DtoMapping dtoMapping;

    @Test
    public void findAll() {
        when(repository.findAll()).thenReturn(Stream.of(new SkillArea(), new SkillArea()).collect(Collectors.toList()));
        assertEquals(2, service.findAll().size());
    }

    @Test
    public void findById() {
        int id = 82;
        SkillArea skillArea = new SkillArea();
        skillArea.setId(id);
        SkillAreaDTO skillAreaDTO = new SkillAreaDTO();
        skillAreaDTO.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(skillArea));
        when(dtoMapping.getDTOFromSkillArea(skillArea)).thenReturn(skillAreaDTO);
        assertEquals(id, service.findById(id).getId());

        when(repository.findById(-1)).thenReturn(Optional.ofNullable(null));
        assertNull(service.findById(-1));
    }

    @Test
    public void save() {
        int id = 82;
        SkillAreaDTO skillAreaDTO = new SkillAreaDTO();
        SkillArea skillArea = new SkillArea();
        skillArea.setId(id);
        skillAreaDTO.setId(id);

        when(repository.save(skillArea)).thenReturn(skillArea);
        when(dtoMapping.getSkillAreaFromDTO(skillAreaDTO)).thenReturn(skillArea);
        when(dtoMapping.getDTOFromSkillArea(skillArea)).thenReturn(skillAreaDTO);
        assertEquals(skillAreaDTO, service.save(skillAreaDTO));

        when(repository.save(null)).thenReturn(null);
        when(dtoMapping.getSkillAreaFromDTO(null)).thenReturn(null);
        when(dtoMapping.getDTOFromSkillArea(null)).thenReturn(null);
        assertNull(service.save(null));
    }
}