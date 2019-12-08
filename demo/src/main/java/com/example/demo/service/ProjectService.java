package com.example.demo.service;

import com.example.demo.model.Project;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> findAll()
    {
        return projectRepository.findAll();
    }

    //todo admin only
    public ProjectDto save(ProjectDto projectDto){
        Project project = DtoMapping.dtoToProject(projectDto);
        project = projectRepository.save(project);
        return DtoMapping.projectToDto(project);
    }

    //todo admin only
    public void deactivate(ProjectDto projectDto){
        projectRepository.findById(projectDto.getId()).ifPresent(project -> {
            project.setStatus(false);
            projectRepository.save(project);
        });
    }

    //todo admin only
    public ProjectDto update(ProjectDto projectDto){
        projectRepository.findById(projectDto.getId()).ifPresent(project -> {
            Project projectnew = DtoMapping.dtoToProject(projectDto);
            project.setName(projectnew.getName());
            project.setDescription(projectnew.getDescription());
            project.setDuration(projectnew.getDuration());
            project.setStatus(projectnew.getStatus());
            project.setCustomer(projectnew.getCustomer());
            project.setIndustry(projectnew.getIndustry());
            projectRepository.save(project);
        });
        if(projectRepository.findById(projectDto.getId()).isPresent()){
            return DtoMapping.projectToDto(projectRepository.findById(projectDto.getId()).get());
        }
        return null;
    }

    //todo admin only
    //update on custommer
    public void assignCustomer(ProjectDto projectDto){
        projectRepository.findById(projectDto.getId()).ifPresent(project -> {
            Project projectnew = DtoMapping.dtoToProject(projectDto);
            project.setCustomer(projectnew.getCustomer());
            projectRepository.save(project);
        });
    }

    //todo admin only
    //update on industry
    public void assignIndustry(ProjectDto projectDto){
        projectRepository.findById(projectDto.getId()).ifPresent(project -> {
            Project projectnew = DtoMapping.dtoToProject(projectDto);
            project.setIndustry(projectnew.getIndustry());
            projectRepository.save(project);
        });
    }



}
