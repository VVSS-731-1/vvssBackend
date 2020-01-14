package com.example.demo.service;

import com.example.demo.model.ConsultingLevel;
import com.example.demo.repository.ConsultingLevelRepository;
import com.example.demo.service.dto.ConsultingLevelDTO;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.interfaces.ConsultingLevelServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ConsultingLevelService implements ConsultingLevelServiceInterface {

    @Autowired
    private ConsultingLevelRepository consultingLevelRepository;

    public List<ConsultingLevel> findAll()
    {
        return consultingLevelRepository.findAll();
    }

    //TODO admin only
    @Transactional
    public ConsultingLevelDTO save(ConsultingLevelDTO consultingLevelDTO) {
        ConsultingLevel consultingLevel = DtoMapping.getConsultingLevelFromDTO(consultingLevelDTO);
        consultingLevel = consultingLevelRepository.save(consultingLevel);

        return DtoMapping.getDTOFromConsultingLevel(consultingLevel);
    }

    //TODO admin only
    public ConsultingLevelDTO deactivateConsultingLevel(ConsultingLevelDTO consultingLevelDTO) {
        ConsultingLevel consultingLevel = consultingLevelRepository.getOne(consultingLevelDTO.getId());

        if(consultingLevel != null) {
            consultingLevel.setStatus(false);
            return DtoMapping.getDTOFromConsultingLevel(consultingLevelRepository.save(consultingLevel));

        }

        return null;
    }


}
