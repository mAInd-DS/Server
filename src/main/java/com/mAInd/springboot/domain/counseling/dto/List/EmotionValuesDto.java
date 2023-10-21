package com.mAInd.springboot.domain.counseling.dto.List;

import com.mAInd.springboot.domain.counseling.entity.EmotionValues;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EmotionValuesDto {
    private Double neutral;
    private Double happy;
    private Double surprise;
    private Double sadness;
    private Double anger;
    private Double disgust;
    private Double fear;

    @Builder
    public EmotionValuesDto(Double neutral, Double happy, Double surprise,
                            Double sadness, Double anger, Double disgust,
                            Double fear){
        this.neutral = neutral;
        this.happy = happy;
        this.surprise = surprise;
        this.sadness = sadness;
        this.anger = anger;
        this.disgust = disgust;
        this.fear = fear;
    }

    public EmotionValuesDto(EmotionValues entity){
        this.neutral = entity.getNeutral();
        this.happy = entity.getHappy();
        this.surprise = entity.getSurprise();
        this.sadness = entity.getSadness();
        this.anger = entity.getAnger();
        this.disgust = entity.getDisgust();
        this.fear = entity.getFear();
    }

    public EmotionValues toEntity(){
        return new EmotionValues(neutral, happy, surprise, sadness, anger, disgust, fear);
    }
}
