package com.example.demo.service.dto;


import lombok.Data;

@Data
public class CustomerDto {
    private Integer id;
    private String name;
    private Boolean status;

    public CustomerDto() {
    }

    public CustomerDto(Integer id, String name, Boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}
