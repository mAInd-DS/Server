package com.mAInd.springboot.domain.mypage.dto;

import com.mAInd.springboot.domain.counseling.entity.ResultOfferStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResultOfferStatusUpdateRequestDto {
    private ResultOfferStatus resultOfferStatus;

    @Builder
    public ResultOfferStatusUpdateRequestDto(ResultOfferStatus resultOfferStatus){
        this.resultOfferStatus = resultOfferStatus;
    }
}
