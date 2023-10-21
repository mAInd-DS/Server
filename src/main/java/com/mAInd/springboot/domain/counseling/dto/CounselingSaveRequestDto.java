package com.mAInd.springboot.domain.counseling.dto;

import com.mAInd.springboot.domain.counseling.entity.Counseling;
import com.mAInd.springboot.domain.surveys.entity.Surveys;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class CounselingSaveRequestDto {
    private Long surveyId;
    private Date date;
    private Long startHour;
    private Long startMin;
    private Long endHour;
    private Long endMin;
    private Long countNum;

    @Builder
    public CounselingSaveRequestDto(Long surveyId, Date date, Long startHour, Long startMin,
                                    Long endHour, Long endMin, Long countNum){
        this.surveyId = surveyId;
        this.date = date;
        this.startHour = startHour;
        this.startMin = startMin;
        this.endHour = endHour;
        this.endMin = endMin;
        this.countNum = countNum;
    }

    public Counseling toEntity(){
        return Counseling.builder()
                .surveyId(surveyId)
                .date(date)
                .startHour(startHour)
                .startMin(startMin)
                .endHour(endHour)
                .endMin(endMin)
                .countNum(countNum)
                .build();
    }

}
