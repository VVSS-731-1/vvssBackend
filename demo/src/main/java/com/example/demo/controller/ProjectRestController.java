package com.example.demo.controller;

import com.example.demo.service.ProjectService;
import com.example.demo.service.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectRestController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/add")
    public ResponseEntity<String> saveProject(@RequestBody ProjectDto projectDto){
        ProjectDto response = projectService.save(projectDto);
        if(response != null){
            return ResponseEntity.ok().body("Project saved!");
        }
        return ResponseEntity.badRequest().body("Project save failed!");
    }

    @PostMapping("/deactivate")
    public ResponseEntity<String> deactivateProject(@RequestBody ProjectDto projectDto){
        projectService.deactivate(projectDto);
        return ResponseEntity.ok().body("Project deactivated!");
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateProect(@RequestBody ProjectDto projectDto){
        ProjectDto response = projectService.update(projectDto);
        if (response != null){
            return ResponseEntity.ok().body("Project updated!");
        }
        return ResponseEntity.badRequest().body("Project update failed!");
    }

    @PostMapping("/assignCustomer")
    public ResponseEntity<String> assignCustomer(@RequestBody ProjectDto projectDto){
        projectService.assignCustomer(projectDto);
        return ResponseEntity.ok().body("Project linked to Customer!");
    }

    @PostMapping("/assignIndustry")
    public ResponseEntity<String> assignIndustry(@RequestBody ProjectDto projectDto){
        projectService.assignIndustry(projectDto);
        return ResponseEntity.ok().body("Project linked to Industry"); // Custom Message Possible String.format("Project %s linked to Industry %s",projectDto.getName(), industryService.find...

    }

}
