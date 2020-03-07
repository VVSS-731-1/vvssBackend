package com.example.demo.controller;

import com.example.demo.service.services.IndustryService;
import com.example.demo.service.dto.IndustryDto;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/industry")
public class IndustryRestContoller {

    @Autowired
    private IndustryService industryService;


    @GetMapping("/getall")
    public ResponseEntity<String> getAll() {
        Gson gson = new Gson();
        String response = gson.toJson(industryService.findAll());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") int id) {
        Gson gson = new Gson();
        if (industryService.findById(id) != null) {
            String response = gson.toJson(industryService.findById(id));
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body("No Industry with given id");
    }

    @PostMapping("/add") //optional consumes = "application/json" produces = "application/json"
    public ResponseEntity<String> saveIndustry(@RequestBody IndustryDto industryDto) {
        IndustryDto response = industryService.save(industryDto);
        if (response != null) {
            return ResponseEntity.ok().body("Industry saved!");
        }
        return ResponseEntity.badRequest().body("Industry save failed!");
    }

    @PostMapping("/deactivate")
    public ResponseEntity<String> deactivateIndustry(@RequestBody IndustryDto industryDto) {
        industryService.deactivateIndustry(industryDto);
        return ResponseEntity.ok().body("Industry deactivated!");
    }
}
