package com.mAInd.springboot.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Getter
public class UserSignUpDto {

    private String email;
    private String password;
    private String name;
}