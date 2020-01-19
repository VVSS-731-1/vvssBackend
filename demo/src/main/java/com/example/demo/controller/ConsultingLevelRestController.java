package com.example.demo.controller;

import com.example.demo.service.ConsultingLevelService;
import com.example.demo.service.dto.ConsultingLevelDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultingLevel")
public class ConsultingLevelRestController {

    @Autowired
    private ConsultingLevelService consultingLevelService;

    @GetMapping("/getall")
    public ResponseEntity<String> getAll() {
        Gson gson = new Gson();
        String response = gson.toJson(consultingLevelService.findAll());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") int id) {
        Gson gson = new Gson();
        if (consultingLevelService.findById(id) != null) {
            String response = gson.toJson(consultingLevelService.findById(id));
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body("No consulting level with given id");
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveConsultingLevel(@RequestBody ConsultingLevelDTO consultingLevelDTO) {
        ConsultingLevelDTO response = consultingLevelService.save(consultingLevelDTO);
        if (response != null) {
            return ResponseEntity.ok().body("Consulting level saved!");
        }
        return ResponseEntity.badRequest().body("Consulting level save failed!");
    }

    @PostMapping("/deactivate")
    public ResponseEntity<String> deactivateConsultingLevel(@RequestBody ConsultingLevelDTO consultingLevelDTO) {
        consultingLevelService.deactivateConsultingLevel(consultingLevelDTO);
        return ResponseEntity.ok().body("Consulting level deactivated!");
    }
}
