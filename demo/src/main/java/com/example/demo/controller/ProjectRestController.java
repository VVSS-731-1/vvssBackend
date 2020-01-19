package com.example.demo.controller;

import com.example.demo.service.ProjectService;
import com.example.demo.service.dto.ProjectDto;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectRestController {

    @Autowired
    private ProjectService projectService;


    @GetMapping("/getall")
    public ResponseEntity<String> getAll(){
        Gson gson = new Gson();
        String response = gson.toJson(projectService.findAll());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") int id){
        Gson gson = new Gson();
        if(projectService.findById(id) != null) {
            String response = gson.toJson(projectService.findById(id));
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body("No Project with given id");
    }

    @PostMapping("/add") //optional consumes = "application/json" produces = "application/json"
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
