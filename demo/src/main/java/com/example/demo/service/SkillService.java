package com.example.demo.service;

import com.example.demo.model.Profile;
import com.example.demo.model.Skill;
import com.example.demo.model.SkillProfile;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.SkillProfileRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.SkillDTO;
import com.example.demo.service.dto.SkillWrapperDTO;
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
    SkillProfileRepository skillProfileRepository;

    @Autowired
    ProfileRepository profileRepository;

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
    public SkillWrapperDTO save(SkillWrapperDTO skillDTO) {

//        Skill skill = dtoMapping.getSkillFromDto(skillDTO);
//        skill = skillRepository.save(skill);
//
//        return dtoMapping.getDTOFromSkill(skill);
        Profile profile = profileRepository.getOne(skillDTO.getProfile_id());

        List<Skill> skills = skillRepository.findAll();
        Skill insertSkill = new Skill();
        for(Skill skill : skills) {
            if(skill.getName().equals(skillDTO.getSkill())) {
                insertSkill = skill;
                break;
            }
        }

        SkillProfile skillProfileToInsert = new SkillProfile();
        skillProfileToInsert.setSkill_id(insertSkill);
        skillProfileToInsert.setProfile_id(profile);
        skillProfileToInsert.setLevel(skillDTO.getSkill_level());
        SkillProfile skillProfile = skillProfileRepository.save(skillProfileToInsert);

        return dtoMapping.skillProfileToWrapperDTO(skillProfile);
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
