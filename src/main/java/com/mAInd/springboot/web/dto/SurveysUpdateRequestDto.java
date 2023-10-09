package com.mAInd.springboot.web.dto;

import com.mAInd.springboot.domain.surveys.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
public class SurveysUpdateRequestDto {
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

    @Builder
    public SurveysUpdateRequestDto(String name, Gender gender, String email, Date birth,
                                     String phone, String education, List<String> symptoms, String q_1, String q_2,
                                     String q_3, String q_4, String q_5, String q_6, String q_7,
                                     String q_8){
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.birth = birth;
        this.phone = phone;
        this.education = education;
        this.symptoms = symptoms;
        this.q_1 = q_1;
        this.q_2 = q_2;
        this.q_3 = q_3;
        this.q_4 = q_4;
        this.q_5 = q_5;
        this.q_6 = q_6;
        this.q_7 = q_7;
        this.q_8 = q_8;
    }
}
