package com.mAInd.springboot.domain.counseling.dto;

import com.mAInd.springboot.domain.counseling.dto.List.EmotionValuesDto;
import com.mAInd.springboot.domain.counseling.dto.List.MergedArrayDto;
import com.mAInd.springboot.domain.counseling.dto.List.SentencePredictionDto;
import com.mAInd.springboot.domain.counseling.dto.List.TotalPercentagesDto;
import com.mAInd.springboot.domain.counseling.entity.Counseling;
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

    private String opinion;
    private List<SentencePredictionDto> sentence_predictions;
    private List<TotalPercentagesDto> total_percentages;
    private List<MergedArrayDto> merged_array;
    private List<EmotionValuesDto> emotion_values;

    public CounselingResponseDto(Counseling entity){
        this.counseling_id = entity.getCounseling_id();
        this.surveyId = entity.getSurveyId();
        this.date = entity.getDate();
        this.startHour = entity.getStartHour();
        this.startMin = entity.getStartMin();
        this.endHour = entity.getEndHour();
        this.endMin = entity.getEndMin();
        this.countNum = entity.getCountNum();
        this.opinion = entity.getOpinion();
        this.sentence_predictions = entity.getSentencePredictions()
                .stream()
                .map(SentencePredictionDto::new)
                .collect(Collectors.toList());
        this.total_percentages = entity.getTotalPercentages()
                .stream()
                .map(TotalPercentagesDto::new)
                .collect(Collectors.toList());
        this.merged_array = entity.getMergedArrays()
                .stream()
                .map(MergedArrayDto::new)
                .collect(Collectors.toList());
        this.emotion_values = entity.getEmotionValues()
                .stream()
                .map(EmotionValuesDto::new)
                .collect(Collectors.toList());
    }


}
