package com.mAInd.springboot.domain.counseling.repository;

import com.mAInd.springboot.domain.counseling.entity.Counseling;
import com.mAInd.springboot.domain.surveys.entity.Surveys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CounselingRepository extends JpaRepository<Counseling, Long> {

    @Query("SELECT c FROM Counseling c ORDER BY c.id DESC")
    List<Surveys> findAllDesc();

}
