package com.example.demo.controller;

import com.example.demo.service.RegionService;
import com.example.demo.service.dto.RegionDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/region")
public class RegionRestController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/getall")
    public ResponseEntity<String> getAll() {
        Gson gson = new Gson();
        String response = gson.toJson(regionService.getAllRegions());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") int id) {
        Gson gson = new Gson();

        if(regionService.findById(id) != null) {
            String response = gson.toJson(regionService.findById(id));
            return ResponseEntity.ok().body(response);
        }

        return ResponseEntity.badRequest().body("No region with given id.");
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveRegion(@RequestBody RegionDTO regionDTO) {
        RegionDTO response = regionService.save(regionDTO);

        if(response != null) {
            return ResponseEntity.ok().body("Region saved!");
        }

        return ResponseEntity.badRequest().body("Region save failed!");
    }

    @PostMapping("/deactivate")
    public ResponseEntity<String> deactivateRegion(@RequestBody RegionDTO regionDTO) {
        regionService.deactivateRegion(regionDTO);
        return ResponseEntity.ok().body("Region deactivated!");
    }
}
