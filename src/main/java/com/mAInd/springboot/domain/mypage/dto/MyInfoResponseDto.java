package com.mAInd.springboot.domain.mypage.dto;

import com.mAInd.springboot.domain.surveys.entity.Surveys;
import com.mAInd.springboot.domain.user.entity.Users;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyInfoResponseDto {

    private String name;
    private String email;
    private LocalDateTime createdDate;

    private String picture;

    private Long survey_id;


    public MyInfoResponseDto(Users entity, Surveys surveyEntity){
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.createdDate = entity.getCreatedDate();
        this.picture = entity.getPicture();
        //Survey가 없는 경우 survey_id = 0 반환(상담자이거나 내담자인데 아직 작성하지 않은 경우)
        if (surveyEntity != null) {
            this.survey_id = surveyEntity.getSurvey_id();
        } else {
            this.survey_id = 0L;
        }

    }


}
