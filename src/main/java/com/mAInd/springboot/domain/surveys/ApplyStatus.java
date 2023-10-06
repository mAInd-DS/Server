package com.mAInd.springboot.domain.surveys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApplyStatus {
    BEFORE("BEFORE", "상담사 확인 전"),
    ACCEPT("ACCEPT", "상담 승인"),
    HOLD("HOLD", "상담 보류"),
    REJECT("REJECT", "상담 반려");

    private final String key;
    private final String title;
}
