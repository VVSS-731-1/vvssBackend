package com.example.demo.service;


import com.example.demo.model.Industry;
import com.example.demo.repository.IndustryRepository;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.IndustryDto;
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

    //todo admin only
    public IndustryDto save(IndustryDto industryDto) {
        Industry industry = DtoMapping.dtoToIndustry(industryDto);
        industry = industryRepository.save(industry);
        return DtoMapping.industryToDto(industry);
    }

    //todo admin only
    public void deactivateIndustry(IndustryDto industryDto) {
        industryRepository.findById(industryDto.getId()).ifPresent(industry -> {
            industry.setStatus(false);
            industryRepository.save(industry);
        });
    }

}
