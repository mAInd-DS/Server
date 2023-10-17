package com.mAInd.springboot.domain.mypage.dto;

import com.mAInd.springboot.domain.surveys.entity.Surveys;
import com.mAInd.springboot.domain.user.entity.UserStatus;
import com.mAInd.springboot.domain.user.entity.Users;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
@Slf4j
@Getter
public class MyStatusResponseDto {
    private UserStatus userStatus;
    private LocalDateTime statusDate;

    public MyStatusResponseDto(Users entity, Surveys surveyEntity){
        this.userStatus = entity.getUserStatus();
        if (surveyEntity != null) {
            this.statusDate = surveyEntity.getStatusDate();
        } else {
            this.statusDate = null;
        }
    }

}
