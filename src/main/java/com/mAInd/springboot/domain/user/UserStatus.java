package com.mAInd.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus {
    BEFORE_SURVEY("BEFORE_SURVEY", "설문지 작성 전"),
    ON_MATCHING("ON_MATCHING", "매칭 중"),
    AFTER_MATCHING("AFTER_MATCHING", "매칭 완료");

    private final String key;
    private final String title;
}
