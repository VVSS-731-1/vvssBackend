package com.example.demo.service;

import com.example.demo.model.SkillArea;
import com.example.demo.repository.SkillAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillAreaService {

    @Autowired
    private SkillAreaRepository skillAreaRepository;

    public List<SkillArea> findAll()
    {
        return skillAreaRepository.findAll();
    }

}
