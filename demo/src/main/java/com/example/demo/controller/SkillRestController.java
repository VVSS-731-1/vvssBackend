package com.example.demo.controller;

import com.example.demo.model.Skill;
import com.example.demo.service.SkillService;
import com.example.demo.service.dto.SkillDTO;
import com.example.demo.service.dto.SkillWrapperDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skill")
public class SkillRestController {

    @Autowired
    private SkillService skillService;

    @GetMapping("/getall")
    public ResponseEntity<String> getAll() {
        Gson gson = new Gson();
        String response = gson.toJson(skillService.findAll());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") int id) {
        Gson gson = new Gson();

        if(skillService.findById(id) != null) {
            String response = gson.toJson(skillService.findById(id));
            return ResponseEntity.ok().body(response);
        }

        return ResponseEntity.badRequest().body("No skill with given id.");
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveSkill(@RequestBody SkillWrapperDTO skillDTO) {
        SkillWrapperDTO response = skillService.save(skillDTO);

        if(response != null) {
            return ResponseEntity.ok().body("Skill saved!");
        }

        return ResponseEntity.badRequest().body("Skill save failed!");
    }

    @PostMapping("/deactivate")
    public ResponseEntity<String> deactivateSkill(@RequestBody SkillDTO skillDTO) {
        skillService.deactivateSkill(skillDTO);
        return ResponseEntity.ok().body("Skill deactivated!");
    }
}
