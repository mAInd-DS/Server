package com.mAInd.springboot.domain.mypage.dto;

import com.mAInd.springboot.domain.surveys.entity.Surveys;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class ClientInfoResponseDto {
    private Long survey_id;
    private String name;
    private Date birth;
    private String email;
    private List<String> symptoms;

    public ClientInfoResponseDto(Surveys entity){
        this.survey_id = entity.getSurvey_id();
        this.name = entity.getName();
        this.birth = entity.getBirth();
        this.email = entity.getEmail();
        this.symptoms = entity.getSymptoms();
    }

}
