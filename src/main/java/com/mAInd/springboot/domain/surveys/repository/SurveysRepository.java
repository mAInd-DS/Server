package com.mAInd.springboot.domain.surveys.repository;

import com.mAInd.springboot.domain.surveys.entity.Surveys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SurveysRepository extends JpaRepository<Surveys, Long> {
    @Query("SELECT s FROM Surveys s ORDER BY s.id DESC")
    List<Surveys> findAllDesc();

    @Query("SELECT s FROM Surveys s WHERE s.counselor_id.user_id = :counselorId")
    List<Surveys> findSurveysByCounselorId(@Param("counselorId") Long counselorId);

//    @Query("SELECT s FROM Surveys s WHERE s.client_id.user_id = :clientId ORDER BY s.survey_id DESC")
    //가장 최근에 작성한 설문지 id 하나만 반환
    @Query("SELECT s FROM Surveys s WHERE s.client_id.user_id = :clientId AND s.survey_id = (SELECT MAX(s2.survey_id) FROM Surveys s2 WHERE s2.client_id.user_id = :clientId)")
    Surveys findLatestSurveyByClientId(@Param("clientId") Long clientId);

    @Query("SELECT s FROM Surveys s WHERE s.counselor_id.user_id = :counselorId AND s.applyStatus = 'ACCEPT'")
    List<Surveys> findAcceptedSurveysByCounselorId(@Param("counselorId") Long counselorId);

}
