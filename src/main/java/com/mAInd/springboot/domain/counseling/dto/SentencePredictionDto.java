package com.mAInd.springboot.domain.counseling.dto;

import com.mAInd.springboot.domain.counseling.entity.Counseling;
import com.mAInd.springboot.domain.counseling.entity.SentencePrediction;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SentencePredictionDto {
    private String sentence;
    private String emotion;

    private Counseling counseling;

    @Builder
    public SentencePredictionDto(String sentence, String emotion, Counseling counseling) {
        this.sentence = sentence;
        this.emotion = emotion;
        this.counseling = counseling;
    }


    public SentencePrediction toEntity() {
        return new SentencePrediction(sentence, emotion, counseling);
    }
}
