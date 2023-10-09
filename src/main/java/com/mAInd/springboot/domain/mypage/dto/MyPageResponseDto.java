package com.mAInd.springboot.domain.mypage.dto;

import com.mAInd.springboot.domain.user.entity.Users;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyPageResponseDto {

    private String name;
    private String email;
    private LocalDateTime createdDate;

    public MyPageResponseDto(Users entity){
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.createdDate = entity.getCreatedDate();
    }


}
