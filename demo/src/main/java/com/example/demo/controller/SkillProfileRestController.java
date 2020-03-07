package com.example.demo.controller;

import com.example.demo.service.dto.SkillWrapperDTO;
import com.example.demo.service.services.SkillProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skill_profile")
public class SkillProfileRestController {

    @Autowired
    private SkillProfileService skillProfileService;

    @PostMapping("/add")
    public ResponseEntity<String> save(@RequestBody SkillWrapperDTO skillDTO) {
        SkillWrapperDTO response = skillProfileService.save(skillDTO);

        if(response != null) {
            return ResponseEntity.ok().body("Skill saved!");
        }

        return ResponseEntity.badRequest().body("Skill save failed!");
    }

}
