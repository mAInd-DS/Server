package com.mAInd.springboot.domain.surveys.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    남("남성"),
    여("여성");

    private String description;
}
