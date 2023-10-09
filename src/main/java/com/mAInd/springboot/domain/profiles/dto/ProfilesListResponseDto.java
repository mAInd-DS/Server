package com.mAInd.springboot.domain.profiles.dto;

import com.mAInd.springboot.domain.profiles.entity.Profiles;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProfilesListResponseDto {
    private Long profile_id;
    private String title;
    private String career;
    private String education;
    private String content;
    private LocalDateTime modifiedDate;

    public ProfilesListResponseDto(Profiles entity){
        this.profile_id = entity.getProfile_id();
        this.title = entity.getTitle();
        this.career = entity.getCareer();
        this.education = entity.getEducation();
        this.content = entity.getContent();
        this.modifiedDate = entity.getModifiedDate();
    }


}
