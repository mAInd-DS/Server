package com.mAInd.springboot.web.dto;

import com.mAInd.springboot.domain.profiles.Profiles;
import lombok.Getter;

@Getter
public class ProfilesResponseDto {

    private Long profile_id;
    private String title;
    private String career;
    private String education;
    private String content;

    public ProfilesResponseDto(Profiles entity){
        this.profile_id = entity.getProfile_id();
        this.title = entity.getTitle();
        this.career = entity.getCareer();
        this.education = entity.getEducation();
        this.content = entity.getContent();
    }
}
