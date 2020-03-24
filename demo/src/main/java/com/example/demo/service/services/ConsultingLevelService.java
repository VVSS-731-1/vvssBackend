package com.example.demo.service.services;

import com.example.demo.model.ConsultingLevel;
import com.example.demo.repository.ConsultingLevelRepository;
import com.example.demo.service.dto.ConsultingLevelDTO;
import com.example.demo.service.dto.DtoMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class ConsultingLevelService {

    @Autowired
    private ConsultingLevelRepository consultingLevelRepository;

    @Autowired
    private DtoMapping dtoMapping;

    public List<ConsultingLevel> findAll()
    {
        return consultingLevelRepository.findAll();
    }

    public ConsultingLevelDTO findById(Integer id) {
        if(consultingLevelRepository.findById(id).isPresent()){
            return dtoMapping.getDTOFromConsultingLevel(consultingLevelRepository.findById(id).get());
        }

        return null;
    }
    //TODO admin only
    public ConsultingLevelDTO save(ConsultingLevelDTO consultingLevelDTO) {
        ConsultingLevel consultingLevel = dtoMapping.getConsultingLevelFromDTO(consultingLevelDTO);
        consultingLevel = consultingLevelRepository.save(consultingLevel);

        return dtoMapping.getDTOFromConsultingLevel(consultingLevel);
    }

    //TODO admin only
    public ConsultingLevel deactivateConsultingLevel(ConsultingLevelDTO consultingLevelDTO) {
        ConsultingLevel consultingLevel = consultingLevelRepository.getOne(consultingLevelDTO.getId());
        ConsultingLevel returnedConsultingLevel = new ConsultingLevel();

        if(consultingLevel != null) {
            consultingLevel.setStatus(false);
            returnedConsultingLevel = consultingLevelRepository.save(consultingLevel);
        }

        return returnedConsultingLevel;
    }


}
