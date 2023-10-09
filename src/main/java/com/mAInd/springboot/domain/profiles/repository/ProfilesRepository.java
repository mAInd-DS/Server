package com.mAInd.springboot.domain.profiles.repository;

import com.mAInd.springboot.domain.profiles.entity.Profiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfilesRepository extends JpaRepository<Profiles, Long>{
    @Query("SELECT p FROM Profiles p ORDER BY p.id DESC")
    List<Profiles> findAllDesc();
}