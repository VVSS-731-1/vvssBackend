package com.example.demo.service.dto;


import lombok.Data;

@Data
public class CustomerDTO {
    private Integer id;
    private String name;
    private Boolean status;

    public CustomerDTO() {
    }

    public CustomerDTO(Integer id, String name, Boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}
