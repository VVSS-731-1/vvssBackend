package com.example.demo.service;


import com.example.demo.model.Industry;
import com.example.demo.repository.IndustryRepository;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.IndustryDTO;
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
    public IndustryDTO save(IndustryDTO industryDto) {
        Industry industry = DtoMapping.dtoToIndustry(industryDto);
        industry = industryRepository.save(industry);
        return DtoMapping.industryToDto(industry);
    }

    //todo admin only
    public IndustryDTO deactivateIndustry(IndustryDTO industryDto) {
        industryRepository.findById(industryDto.getId()).ifPresent(industry -> {
            industry.setStatus(false);
            industryRepository.save(industry);
            industryDto.setStatus(false);
        });

        return industryDto;
    }

}
