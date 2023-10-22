package com.mAInd.springboot.domain.counseling.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResultOfferStatus {
    BEFORE("BEFORE", "결과지 제공 전"),
    ACCEPT("ACCEPT", "결과제 제공 승인");

    private final String key;
    private final String title;
}
