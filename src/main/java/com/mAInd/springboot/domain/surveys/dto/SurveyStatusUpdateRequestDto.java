package com.mAInd.springboot.domain.surveys.dto;

import com.mAInd.springboot.domain.surveys.entity.ApplyStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SurveyStatusUpdateRequestDto {
    private ApplyStatus applyStatus;
    private LocalDateTime statusDate;

    @Builder
    SurveyStatusUpdateRequestDto(ApplyStatus applyStatus, LocalDateTime statusDate){
        this.applyStatus = applyStatus;
        this.statusDate = statusDate;
    }

}
