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
        this.survey_id = surveyEntity.getSurvey_id();
    }


}
