package com.example.demo.service.dto;

import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Boolean admin;
    private List<ProjectDto> projects;

    private Boolean status;

    private UserDTO supervisor;

    private Set<UserDTO> supervising;

    public UserDTO() {

    }
}
