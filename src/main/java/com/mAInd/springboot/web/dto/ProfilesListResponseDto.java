package com.mAInd.springboot.web.dto;

import com.mAInd.springboot.domain.profiles.Profiles;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProfilesListResponseDto {
    private Long counselor_profile_id;
    private String title;
    private String career;
    private String education;
    private String content;
    private String counselor_id;
    private LocalDateTime modifiedDate;

    public ProfilesListResponseDto(Profiles entity){
        this.counselor_profile_id = entity.getCounselor_profile_id();
        this.title = entity.getTitle();
        this.career = entity.getCareer();
        this.education = entity.getEducation();
        this.content = entity.getContent();
        this.counselor_id = entity.getCounselor_id();
        this.modifiedDate = entity.getModifiedDate();
    }


}
