package com.mAInd.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    COUNSELOR("ROLE_COUNSELOR", "상담자"),
    CLIENT("ROLE_CLIENT", "내담자");

    private final String key;
    private final String title;
}
