package com.example.demo.controller;

import com.example.demo.service.services.SkillAreaService;
import com.example.demo.service.dto.SkillAreaDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("skillArea")
public class SkillAreaRestController {

    @Autowired
    private SkillAreaService skillAreaService;

    @GetMapping("/getall")
    public ResponseEntity<String> getAll() {
        Gson gson = new Gson();
        String response = gson.toJson(skillAreaService.findAll());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") int id) {
        Gson gson = new Gson();

        if(skillAreaService.findById(id) != null) {
            String response = gson.toJson(skillAreaService.findById(id));
            return ResponseEntity.ok().body(response);
        }

        return ResponseEntity.badRequest().body("No skill with given id.");
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveSkill(@RequestBody SkillAreaDTO skillAreaDTO) {
        SkillAreaDTO response = skillAreaService.save(skillAreaDTO);

        if(response != null) {
            return ResponseEntity.ok().body("Skill area saved!");
        }

        return ResponseEntity.badRequest().body("Skill area failed!");
    }

    @PostMapping("/deactivate")
    public ResponseEntity<String> deactivateSkill(@RequestBody SkillAreaDTO skillAreaDTO) {
        skillAreaService.deactivateSkillArea(skillAreaDTO);
        return ResponseEntity.ok().body("Skill area deactivated!");
    }
}
