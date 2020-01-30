package com.example.demo.repository;

import com.example.demo.model.SkillProfile;
import com.example.demo.model.SkillProfileKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@Component
public interface SkillProfileRepository extends JpaRepository<SkillProfile, SkillProfileKey> {
}
