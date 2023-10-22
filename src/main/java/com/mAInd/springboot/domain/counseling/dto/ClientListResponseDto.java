package com.mAInd.springboot.domain.counseling.dto;

import com.mAInd.springboot.domain.surveys.entity.ApplyStatus;
import com.mAInd.springboot.domain.surveys.entity.Surveys;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class ClientListResponseDto {
    private Long survey_id;
    private String name;
    private Date birth;
    private List<String> symptoms;
    private ApplyStatus applyStatus;

    public ClientListResponseDto(Surveys entity){
        this.survey_id = entity.getSurvey_id();
        this.name = entity.getName();
        this.birth = entity.getBirth();
        this.symptoms = entity.getSymptoms();
        this.applyStatus = entity.getApplyStatus();
    }

}
