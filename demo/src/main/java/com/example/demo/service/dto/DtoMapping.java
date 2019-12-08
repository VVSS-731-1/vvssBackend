package com.example.demo.service.dto;

import com.example.demo.model.Customer;
import com.example.demo.model.Industry;
import com.example.demo.model.Project;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.IndustryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DtoMapping {

    @Autowired
    private static IndustryRepository industryRepository;

    @Autowired
    private static CustomerRepository customerRepository;

    public static Project dtoToProject(ProjectDto projectDto) {
        Project project = new Project();
        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setDuration(projectDto.getDuration());
        project.setStatus(projectDto.getStatus());
        customerRepository.findById(projectDto.getCustomerId()).ifPresent(project::setCustomer);
        industryRepository.findById(projectDto.getIndustryId()).ifPresent(project::setIndustry);
        return project;
    }

    public static ProjectDto projectToDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setDescription(project.getDescription());
        projectDto.setDuration(project.getDuration());
        projectDto.setStatus(project.getStatus());
        projectDto.setCustomerId(project.getCustomer().getId());
        projectDto.setIndustryId(project.getIndustry().getId());
        return projectDto;
    }

    public static Customer dtoToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setStatus(customerDto.getStatus());
        return customer;
    }

    public static CustomerDto customerToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setStatus(customer.getStatus());
        return customerDto;
    }

    public static Industry dtoToIndustry(IndustryDto industryDto) {
        Industry industry = new Industry();
        industry.setId(industryDto.getId());
        industry.setName(industryDto.getName());
        industry.setDescription(industryDto.getDescription());
        industry.setStatus(industryDto.getStatus());
        return industry;
    }

    public static IndustryDto industryToDto(Industry industry) {
        IndustryDto industryDto = new IndustryDto();
        industryDto.setId(industry.getId());
        industryDto.setName(industry.getName());
        industryDto.setDescription(industry.getDescription());
        industryDto.setStatus(industry.getStatus());
        return industryDto;
    }

}
