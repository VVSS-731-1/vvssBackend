package com.example.demo.service;

import com.example.demo.model.ConsultingLevel;
import com.example.demo.repository.ConsultingLevelRepository;
import com.example.demo.service.interfaces.ConsultingLevelServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultingLevelService implements ConsultingLevelServiceInterface {

    @Autowired
    private ConsultingLevelRepository consultingLevelRepository;

    public List<ConsultingLevel> findAll()
    {
        return consultingLevelRepository.findAll();
    }




}
