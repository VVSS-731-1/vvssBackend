package com.example.demo.service;

import com.example.demo.model.SkillArea;
import com.example.demo.repository.SkillAreaRepository;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.SkillAreaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillAreaService {

    @Autowired
    private SkillAreaRepository skillAreaRepository;

    public List<SkillArea> findAll()
    {
        return skillAreaRepository.findAll();
    }

    //TODO admin only
    public SkillAreaDTO save(SkillAreaDTO skillAreaDTO) {
        SkillArea skillArea = DtoMapping.getSkillAreaFromDTO(skillAreaDTO);
        skillArea = skillAreaRepository.save(skillArea);

        return DtoMapping.getDTOFromSkillArea(skillArea);
    }

    //TODO admin only
    public void deactivateSkillArea(SkillAreaDTO skillAreaDTO) {
        SkillArea skillArea = skillAreaRepository.getOne(skillAreaDTO.getId());

        if(skillArea != null) {
            skillArea.setStatus(false);
            skillAreaRepository.save(skillArea);
        }
    }
}
