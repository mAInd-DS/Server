package com.mAInd.springboot.web.dto;

import com.mAInd.springboot.domain.profiles.Profiles;
import lombok.Getter;

@Getter
public class ProfilesResponseDto {

    private Long counselor_profile_id;
    private String title;
    private String career;
    private String education;
    private String content;
    private String counselor_id;

    public ProfilesResponseDto(Profiles entity){
        this.counselor_profile_id = entity.getCounselor_profile_id();
        this.title = entity.getTitle();
        this.career = entity.getCareer();
        this.education = entity.getEducation();
        this.content = entity.getContent();
        this.counselor_id = entity.getCounselor_id();
    }
}
