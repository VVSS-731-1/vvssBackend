package com.example.demo.controller;

import com.example.demo.service.ProfileService;
import com.example.demo.service.dto.ProfileDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileRestController {
    
    @Autowired
    private ProfileService profileService;

    @GetMapping("/getall")
    public ResponseEntity<String> getAll() {
        Gson gson = new Gson();
        String response = gson.toJson(profileService.findAll());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") int id) {
        Gson gson = new Gson();
        if (profileService.findById(id) != null) {
            String response = gson.toJson(profileService.findById(id));
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body("No Profile with given id");
    }

    @GetMapping("/get_profile/{username}")
    public ResponseEntity<String> getByUsername(@PathVariable("username") String username) {
        Gson gson = new Gson();

        ProfileDTO profileDTO = profileService.findByUsername(username);
        if(profileDTO != null) {
            String response = gson.toJson(profileDTO);
            return ResponseEntity.ok().body(response);
        }
        else {
            return ResponseEntity.badRequest().body("No profile found with given username.");
        }

    }
    
    @PostMapping("/add") 
    public ResponseEntity<String> saveProfile(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO response = profileService.saveProfile(profileDTO);
        if (response != null) {
            return ResponseEntity.ok().body("Profile saved!");
        }
        return ResponseEntity.badRequest().body("Profile save failed!");
    }

    @PostMapping("/deactivate")
    public ResponseEntity<String> deactivateProfile(@RequestBody ProfileDTO profileDTO) {
        profileService.deactivate(profileDTO);
        return ResponseEntity.ok().body("Profile deactivated!");
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateProfile(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO response = profileService.updateProfile(profileDTO);
        if (response != null) {
            return ResponseEntity.ok().body("Profile updated!");
        }
        return ResponseEntity.badRequest().body("Profile update failed!");
    }

    @PostMapping("/assignRegion")
    public ResponseEntity<String> assignRegion(@RequestBody ProfileDTO profileDTO) {
        profileService.assignRegion(profileDTO);
        return ResponseEntity.ok().body("Profile linked to region!");
    }

    @PostMapping("/assignConsultingLevel")
    public ResponseEntity<String> assignConsultingLevel(@RequestBody ProfileDTO profileDTO) {
        profileService.assignConsultingLevel(profileDTO);
        return ResponseEntity.ok().body("Profile linked to consulting level!");
    }

    @PostMapping("/assignSkillProfile")
    public ResponseEntity<String> assignSkillProfile(@RequestBody ProfileDTO profileDTO) {
        profileService.assignSkillProfile(profileDTO);
        return ResponseEntity.ok().body("Profile linked to skill profile!");
    }
}
