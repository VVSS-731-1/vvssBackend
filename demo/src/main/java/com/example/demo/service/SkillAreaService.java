package com.example.demo.service;

import com.example.demo.model.SkillArea;
import com.example.demo.repository.SkillAreaRepository;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.SkillAreaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class SkillAreaService {

    @Autowired
    private SkillAreaRepository skillAreaRepository;

    @Autowired
    private DtoMapping dtoMapping;

    public List<SkillArea> findAll()
    {
        return skillAreaRepository.findAll();
    }

    public SkillAreaDTO findById(Integer id) {
        if(skillAreaRepository.findById(id).isPresent()){
            return dtoMapping.getDTOFromSkillArea(skillAreaRepository.findById(id).get());
        }

        return null;
    }

    //TODO admin only
    public SkillAreaDTO save(SkillAreaDTO skillAreaDTO) {
        SkillArea skillArea = dtoMapping.getSkillAreaFromDTO(skillAreaDTO);
        skillArea = skillAreaRepository.save(skillArea);

        return dtoMapping.getDTOFromSkillArea(skillArea);
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
