package com.example.demo.service;

import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.SkillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private DtoMapping dtoMapping;

    public List<Skill> findAll()
    {
        return skillRepository.findAll();
    }

    public SkillDTO findById(Integer id) {
        if(skillRepository.findById(id).isPresent()){
            return dtoMapping.getDTOFromSkill(skillRepository.findById(id).get());
        }

        return null;
    }

    //TODO admin only
    public SkillDTO save(SkillDTO skillDTO) {

        Skill skill = dtoMapping.getSkillFromDto(skillDTO);
        skill = skillRepository.save(skill);

        return dtoMapping.getDTOFromSkill(skill);
    }

    //TODO admin only
    public void deactivateSkill(SkillDTO skillDTO) {
        Skill skill = skillRepository.getOne(skillDTO.getId());

        if(skill != null) {
            skill.setStatus(false);
            skillRepository.save(skill);
        }
    }
}
