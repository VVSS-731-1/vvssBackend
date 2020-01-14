package com.example.demo.service;

import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.SkillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> findAll()
    {
        return skillRepository.findAll();
    }

    //TODO admin only
    public SkillDTO save(SkillDTO skillDTO) {

        Skill skill = DtoMapping.getSkillFromDto(skillDTO);
        skill = skillRepository.save(skill);

        return DtoMapping.getDTOFromSkill(skill);
    }

    //TODO admin only
    public SkillDTO deactivateSkill(SkillDTO skillDTO) {
        Skill skill = skillRepository.getOne(skillDTO.getId());

        if(skill != null) {
            skill.setStatus(false);
            return DtoMapping.getDTOFromSkill(skillRepository.save(skill));
        }

        return null;
    }
}
