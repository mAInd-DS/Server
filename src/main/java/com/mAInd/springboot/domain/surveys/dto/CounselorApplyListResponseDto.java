package com.mAInd.springboot.domain.surveys.dto;

import com.mAInd.springboot.domain.surveys.entity.ApplyStatus;
import com.mAInd.springboot.domain.surveys.entity.Surveys;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
public class CounselorApplyListResponseDto {
    private Long survey_id;
    private String name;
    private Date birth;
    private List<String> symptoms;
    private LocalDateTime createdDate;
    private ApplyStatus applyStatus;
    private LocalDateTime statusDate;

    public CounselorApplyListResponseDto(Surveys entity){
        this.survey_id = entity.getSurvey_id();
        this.name = entity.getName();
        this.birth = entity.getBirth();
        this.symptoms = entity.getSymptoms();
        this.createdDate = entity.getCreatedDate();
        this.applyStatus = entity.getApplyStatus();
        this.statusDate = entity.getStatusDate();
    }
}
