package com.mAInd.springboot.domain.mypage.dto;

import com.mAInd.springboot.domain.counseling.entity.Counseling;
import com.mAInd.springboot.domain.surveys.entity.Surveys;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class IndividualCounselingListResponseDto {
    private Long counseling_id;
    private Long countNum;
    private Date date;
    private String name;
    private List<String> symptoms;

    public IndividualCounselingListResponseDto(Counseling counselingEntity, Surveys surveyEntity){
        this.counseling_id = counselingEntity.getCounseling_id();
        this.countNum = counselingEntity.getCountNum();
        this.date = counselingEntity.getDate();
        this.name = surveyEntity.getName();
        this.symptoms = surveyEntity.getSymptoms();
    }


}
