package com.mAInd.springboot.domain.counseling.dto.List;

import com.mAInd.springboot.domain.counseling.entity.TotalPercentages;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TotalPercentagesDto {
    private String emotion;
    private Double percentage;

    @Builder
    public TotalPercentagesDto(String emotion, Double percentage){
        this.emotion = emotion;
        this.percentage = percentage;
    }

    public TotalPercentagesDto(TotalPercentages entity){
        this.emotion = entity.getEmotion();
        this.percentage = entity.getPercentage();
    }

    public TotalPercentages toEntity(){
        return new TotalPercentages(emotion, percentage);
    }
}
