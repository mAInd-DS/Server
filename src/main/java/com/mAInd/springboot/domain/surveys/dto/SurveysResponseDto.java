package com.mAInd.springboot.domain.surveys.dto;

import com.mAInd.springboot.domain.surveys.entity.ApplyStatus;
import com.mAInd.springboot.domain.surveys.entity.Gender;
import com.mAInd.springboot.domain.surveys.entity.Surveys;
import com.mAInd.springboot.domain.user.entity.Users;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class SurveysResponseDto {
    private Long survey_id;
    private String name;
    private Gender gender;
    private String email;
    private Date birth;
    private String phone;
    private String education;
    private List<String> symptoms;
    private String q_1;
    private String q_2;
    private String q_3;
    private String q_4;
    private String q_5;
    private String q_6;
    private String q_7;
    private String q_8;

    //    private Long counselor_id;

    private ApplyStatus applyStatus;


    public SurveysResponseDto(Surveys entity){
        this.survey_id = entity.getSurvey_id();
        this.name = entity.getName();
        this.gender = entity.getGender();
        this.email = entity.getEmail();
        this.birth = entity.getBirth();
        this.phone = entity.getPhone();
        this.education = entity.getEducation();
        this.symptoms = entity.getSymptoms();
        this.q_1 = entity.getQ_1();
        this.q_2 = entity.getQ_2();
        this.q_3 = entity.getQ_3();
        this.q_4 = entity.getQ_4();
        this.q_5 = entity.getQ_5();
        this.q_6 = entity.getQ_6();
        this.q_7 = entity.getQ_7();
        this.q_8 = entity.getQ_8();
        this.applyStatus = entity.getApplyStatus();
    }
}
