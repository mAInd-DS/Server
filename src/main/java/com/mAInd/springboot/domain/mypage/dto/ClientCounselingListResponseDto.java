package com.mAInd.springboot.domain.mypage.dto;

import com.mAInd.springboot.domain.counseling.entity.Counseling;
import com.mAInd.springboot.domain.counseling.entity.ResultOfferStatus;
import com.mAInd.springboot.domain.surveys.entity.Surveys;
import com.mAInd.springboot.domain.surveys.repository.SurveysRepository;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class ClientCounselingListResponseDto {
    private Long survey_id;
    private Long counseling_id;
    private Long countNum;
    private Date date;
    private String name;
    private List<String> symptoms;
    private ResultOfferStatus resultOfferStatus;

    public ClientCounselingListResponseDto(Counseling counselingEntity, SurveysRepository surveysRepository){
        this.survey_id = counselingEntity.getSurveyId();

        Surveys surveysEntity = surveysRepository.findLatestSurveyByClientId(survey_id);
        this.name = surveysEntity.getName();
        this.symptoms = surveysEntity.getSymptoms();

        this.countNum = counselingEntity.getCountNum();
        this.counseling_id = counselingEntity.getCounseling_id();
        this.date = counselingEntity.getDate();
        this.resultOfferStatus = counselingEntity.getResultOfferStatus();
    }
}
