package com.mAInd.springboot.domain.Surveys;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    M("����"),
    F("����");

    private String description;
}
