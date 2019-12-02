package com.example.demo.service;


import com.example.demo.model.Industry;
import com.example.demo.repository.IndustryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndustryService {

    @Autowired
    private IndustryRepository industryRepository;

    public List<Industry> findAll()
    {
        return industryRepository.findAll();
    }

}
