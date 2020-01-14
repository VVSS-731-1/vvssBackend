package com.example.demo.service.interfaces;

import com.example.demo.model.ConsultingLevel;
import com.example.demo.service.dto.ConsultingLevelDTO;

import java.util.List;

public interface ConsultingLevelServiceInterface {

    List<ConsultingLevel> findAll();
    ConsultingLevelDTO save(ConsultingLevelDTO consultingLevelDTO);
    ConsultingLevelDTO deactivateConsultingLevel(ConsultingLevelDTO consultingLevelDTO);
}
