package com.example.demo.service;

import com.example.demo.model.ConsultingLevel;
import com.example.demo.repository.ConsultingLevelRepository;
import com.example.demo.service.dto.ConsultingLevelDTO;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.interfaces.ConsultingLevelServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class ConsultingLevelService implements ConsultingLevelServiceInterface {

    @Autowired
    private ConsultingLevelRepository consultingLevelRepository;

    @Autowired
    private DtoMapping dtoMapping;

    public List<ConsultingLevel> findAll()
    {
        return consultingLevelRepository.findAll();
    }

    //TODO admin only
    public ConsultingLevelDTO save(ConsultingLevelDTO consultingLevelDTO) {
        ConsultingLevel consultingLevel = dtoMapping.getConsultingLevelFromDTO(consultingLevelDTO);
        consultingLevel = consultingLevelRepository.save(consultingLevel);

        return dtoMapping.getDTOFromConsultingLevel(consultingLevel);
    }

    //TODO admin only
    public void deactivateConsultingLevel(ConsultingLevelDTO consultingLevelDTO) {
        ConsultingLevel consultingLevel = consultingLevelRepository.getOne(consultingLevelDTO.getId());

        if(consultingLevel != null) {
            consultingLevel.setStatus(false);
            consultingLevelRepository.save(consultingLevel);
        }
    }


}
