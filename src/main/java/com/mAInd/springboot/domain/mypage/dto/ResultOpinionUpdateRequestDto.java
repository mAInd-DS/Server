package com.mAInd.springboot.domain.mypage.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor
public class ResultOpinionUpdateRequestDto {
    private String opinion;

    @Builder
    public  ResultOpinionUpdateRequestDto(String opinion){
        this.opinion = opinion;
    }

}
