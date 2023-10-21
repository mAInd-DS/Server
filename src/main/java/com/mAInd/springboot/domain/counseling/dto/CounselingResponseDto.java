package com.mAInd.springboot.domain.counseling.dto;

import com.mAInd.springboot.domain.counseling.entity.Counseling;
import com.mAInd.springboot.domain.counseling.entity.SentencePrediction;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CounselingResponseDto {
    private Long counseling_id;
    private Long surveyId;
    private Date date;
    private Long startHour;
    private Long startMin;
    private Long endHour;
    private Long endMin;
    private Long countNum;
    private List<SentencePredictionDto> sentence_predictions;
    private List<TotalPercentagesDto> total_percentages;

    public CounselingResponseDto(Counseling entity){
        this.counseling_id = entity.getCounseling_id();
        this.surveyId = entity.getSurveyId();
        this.date = entity.getDate();
        this.startHour = entity.getStartHour();
        this.startMin = entity.getStartMin();
        this.endHour = entity.getEndHour();
        this.endMin = entity.getEndMin();
        this.countNum = entity.getCountNum();
        this.sentence_predictions = entity.getSentencePredictions()
                .stream()
                .map(SentencePredictionDto::new)
                .collect(Collectors.toList());
        this.total_percentages = entity.getTotalPercentages()
                .stream()
                .map(TotalPercentagesDto::new)
                .collect(Collectors.toList());
    }


}
