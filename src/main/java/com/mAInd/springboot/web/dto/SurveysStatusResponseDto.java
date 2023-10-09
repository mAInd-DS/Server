package com.mAInd.springboot.web.dto;

import com.google.type.DateTime;
import com.mAInd.springboot.domain.surveys.ApplyStatus;
import com.mAInd.springboot.domain.surveys.Surveys;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SurveysStatusResponseDto {
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private LocalDateTime statusDate;
    private ApplyStatus applyStatus;

    public SurveysStatusResponseDto(Surveys entity){
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
        this.statusDate = entity.getStatusDate();
        this.applyStatus = entity.getApplyStatus();
    }

}
