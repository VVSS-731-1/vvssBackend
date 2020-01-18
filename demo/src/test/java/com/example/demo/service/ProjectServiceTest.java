package com.example.demo.service;

import com.example.demo.model.Project;
import com.example.demo.service.dto.ProjectDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @Test
    void findAll() {

        List<Project> result = projectService.findAll();


        Gson gson = new Gson();
        ProjectDto projectDto = new ProjectDto("testname","testDescription",true, (long) 7,1,1);
        System.out.println("----------------------");
        System.out.println(gson.toJson(projectDto));
        System.out.println("----------------------");
        assertEquals(1, result.size());

    }
}