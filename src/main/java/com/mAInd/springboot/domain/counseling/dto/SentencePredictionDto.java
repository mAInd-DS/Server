package com.mAInd.springboot.domain.counseling.dto;

import com.mAInd.springboot.domain.counseling.entity.SentencePrediction;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SentencePredictionDto {
    private String sentence;
    private String emotion;

    @Builder
    public SentencePredictionDto(String sentence, String emotion) {
        this.sentence = sentence;
        this.emotion = emotion;
    }

    public SentencePredictionDto(SentencePrediction entity) {
        this.sentence = entity.getSentence();
        this.emotion = entity.getEmotion();
    }


    public SentencePrediction toEntity() {
        return new SentencePrediction(sentence, emotion);
    }
}