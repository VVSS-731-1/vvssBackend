package com.example.demo.controller;

import com.example.demo.service.dto.SkillDTO;
import com.example.demo.service.services.SkillService;
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

    @PostMapping("/deactivate")
    public ResponseEntity<String> deactivateSkill(@RequestBody SkillDTO skillDTO) {
        skillService.deactivateSkill(skillDTO);
        return ResponseEntity.ok().body("Skill deactivated!");
    }
}
