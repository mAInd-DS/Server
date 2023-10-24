package com.mAInd.springboot.domain.mypage.dto;

import com.mAInd.springboot.domain.counseling.dto.List.EmotionValuesDto;
import com.mAInd.springboot.domain.counseling.dto.List.MergedArrayDto;
import com.mAInd.springboot.domain.counseling.dto.List.SentencePredictionDto;
import com.mAInd.springboot.domain.counseling.dto.List.TotalPercentagesDto;
import com.mAInd.springboot.domain.counseling.entity.Counseling;
import com.mAInd.springboot.domain.counseling.entity.ResultOfferStatus;
import com.mAInd.springboot.domain.surveys.entity.Surveys;
import com.mAInd.springboot.domain.user.entity.Users;
import lombok.Getter;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CounselingResultResponseDto {

    private Date date;

    private Long startHour;
    private Long startMin;
    private Long endHour;
    private Long endMin;

    private Long countNum;

    private List<String> symptoms;

    private String clientName;
    private String counselorName;

    private String opinion;

    private ResultOfferStatus resultOfferStatus;
    private List<SentencePredictionDto> sentence_predictions;
    private List<TotalPercentagesDto> total_percentages;
    private List<MergedArrayDto> merged_array;
    private List<EmotionValuesDto> emotion_values;

    public CounselingResultResponseDto(Counseling counselingEntity,
                                       Surveys surveyEntity){
        this.date = counselingEntity.getDate();
        this.startHour = counselingEntity.getStartHour();
        this.startMin = counselingEntity.getStartMin();
        this.endHour = counselingEntity.getEndHour();
        this.endMin = counselingEntity.getEndMin();
        this.countNum = counselingEntity.getCountNum();
        this.symptoms = surveyEntity.getSymptoms();
        this.clientName = surveyEntity.getClient_id().getName();
        this.counselorName = surveyEntity.getCounselor_id().getName();
        this.opinion = counselingEntity.getOpinion();
        this.resultOfferStatus = counselingEntity.getResultOfferStatus();
        this.sentence_predictions = counselingEntity.getSentencePredictions()
                .stream()
                .map(SentencePredictionDto::new)
                .collect(Collectors.toList());
        this.total_percentages = counselingEntity.getTotalPercentages()
                .stream()
                .map(TotalPercentagesDto::new)
                .collect(Collectors.toList());
        this.merged_array = counselingEntity.getMergedArrays()
                .stream()
                .map(MergedArrayDto::new)
                .collect(Collectors.toList());
        this.emotion_values = counselingEntity.getEmotionValues()
                .stream()
                .map(EmotionValuesDto::new)
                .collect(Collectors.toList());
    }


}
