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
    private Long survey_id;
    private Date date;
    private Long startHour;
    private Long startMin;
    private Long endHour;
    private Long endMin;
    private Long countNum;

//    public void setSurveyId(Surveys survey){
//        this.survey_id = survey;
//    }

    @Builder
    public CounselingSaveRequestDto(Long survey_id, Date date, Long startHour, Long startMin,
                                    Long endHour, Long endMin, Long countNum){
        this.survey_id = survey_id;
        this.date = date;
        this.startHour = startHour;
        this.startMin = startMin;
        this.endHour = endHour;
        this.endMin = endMin;
        this.countNum = countNum;
    }

    public Counseling toEntity(){
        return Counseling.builder()
                .survey_id(survey_id)
                .date(date)
                .startHour(startHour)
                .startMin(startMin)
                .endHour(endHour)
                .endMin(endMin)
                .countNum(countNum)
                .build();
    }

}
