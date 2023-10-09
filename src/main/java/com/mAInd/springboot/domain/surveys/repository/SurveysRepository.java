package com.mAInd.springboot.domain.surveys.repository;

import com.mAInd.springboot.domain.surveys.entity.Surveys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveysRepository extends JpaRepository<Surveys, Long> {
    @Query("SELECT s FROM Surveys s ORDER BY s.id DESC")
    List<Surveys> findAllDesc();


}
