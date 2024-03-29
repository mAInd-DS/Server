package com.mAInd.springboot.domain.counseling.repository;

import com.mAInd.springboot.domain.counseling.entity.Counseling;
import com.mAInd.springboot.domain.surveys.entity.Surveys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CounselingRepository extends JpaRepository<Counseling, Long> {

    @Query("SELECT c FROM Counseling c ORDER BY c.id DESC")
    List<Surveys> findAllDesc();

    Counseling findBySurveyIdAndCountNum(Long survey_id, Long countNum);

    List<Counseling> findBySurveyId(@Param("surveyId") Long surveyId);

}
