package com.mAInd.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfilesUpdateRequestDto {
    private String title;
    private String career;
    private String education;
    private String content;

    @Builder
    public ProfilesUpdateRequestDto(String title, String career, String education, String content){
        this.title = title;
        this.career = career;
        this.education = education;
        this.content = content;
    }
}
