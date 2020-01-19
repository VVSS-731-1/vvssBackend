package com.example.demo.service;

import com.example.demo.model.Project;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Component
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private DtoMapping dtoMapping;

    public List<ProjectDto> findAll() {
        return projectRepository.findAll().stream().map(dtoMapping::projectToDto).collect(Collectors.toList());
    }

    public ProjectDto findById(Integer id){
        if (projectRepository.findById(id).isPresent())
            return dtoMapping.projectToDto(projectRepository.findById(id).get());
        return null;
    }

    //todo admin only
    public ProjectDto save(ProjectDto projectDto) {
        Project project = dtoMapping.dtoToProject(projectDto);
        project = projectRepository.save(project);
        return dtoMapping.projectToDto(project);
    }

    //todo admin only
    public void deactivate(ProjectDto projectDto) {
        projectRepository.findById(projectDto.getId()).ifPresent(project -> {
            project.setStatus(false);
            projectRepository.save(project); // update through save
        });
    }

    //todo admin only
    public ProjectDto update(ProjectDto projectDto) {
        projectRepository.findById(projectDto.getId()).ifPresent(project -> {
            Project projectnew = dtoMapping.dtoToProject(projectDto);
            project.setName(projectnew.getName());
            project.setDescription(projectnew.getDescription());
            project.setDuration(projectnew.getDuration());
            project.setStatus(projectnew.getStatus());
            project.setCustomer(projectnew.getCustomer());
            project.setIndustry(projectnew.getIndustry());
            projectRepository.save(project);
        });
        if (projectRepository.findById(projectDto.getId()).isPresent()) {
            return dtoMapping.projectToDto(projectRepository.findById(projectDto.getId()).get());
        }
        return null;
    }

    //todo admin only
    //update on custommer
    public void assignCustomer(ProjectDto projectDto) {
        projectRepository.findById(projectDto.getId()).ifPresent(project -> {
            Project projectnew = dtoMapping.dtoToProject(projectDto);
            project.setCustomer(projectnew.getCustomer());
            projectRepository.save(project);
        });
    }

    //todo admin only
    //update on industry
    public void assignIndustry(ProjectDto projectDto) {
        projectRepository.findById(projectDto.getId()).ifPresent(project -> {
            Project projectnew = dtoMapping.dtoToProject(projectDto);
            project.setIndustry(projectnew.getIndustry());
            projectRepository.save(project);
        });
    }


}
