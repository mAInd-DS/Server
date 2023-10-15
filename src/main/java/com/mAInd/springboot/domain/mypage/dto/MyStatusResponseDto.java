package com.mAInd.springboot.domain.mypage.dto;

import com.mAInd.springboot.domain.user.entity.UserStatus;
import com.mAInd.springboot.domain.user.entity.Users;
import lombok.Getter;

@Getter
public class MyStatusResponseDto {
    private UserStatus userStatus;

    public MyStatusResponseDto(Users entity){
        this.userStatus = entity.getUserStatus();
    }

}
