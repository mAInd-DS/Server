package com.mAInd.springboot.domain.counseling.dto;

import com.mAInd.springboot.domain.counseling.entity.Counseling;
import com.mAInd.springboot.domain.surveys.entity.Surveys;
import lombok.Getter;

import java.util.Date;

@Getter
public class CounselingResponseDto {
    private Long counseling_id;
    private Long survey_id;
    private Date date;
    private Long startHour;
    private Long startMin;
    private Long endHour;
    private Long endMin;
    private Long countNum;
    public CounselingResponseDto(Counseling entity){
        this.counseling_id = entity.getCounseling_id();
        this.survey_id = entity.getSurvey_id();
        this.date = entity.getDate();
        this.startHour = entity.getStartHour();
        this.startMin = entity.getStartMin();
        this.endHour = entity.getEndHour();
        this.endMin = entity.getEndMin();
        this.countNum = entity.getCountNum();
    }

}
