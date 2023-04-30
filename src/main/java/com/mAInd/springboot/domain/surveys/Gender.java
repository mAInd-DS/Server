package com.mAInd.springboot.domain.surveys;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    M("남성"),
    F("여성");

    private String description;
}
