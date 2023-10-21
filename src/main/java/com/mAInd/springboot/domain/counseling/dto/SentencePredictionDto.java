package com.mAInd.springboot.domain.counseling.dto;

import com.mAInd.springboot.domain.counseling.entity.Results;
import com.mAInd.springboot.domain.counseling.entity.SentencePrediction;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SentencePredictionDto {
    private String sentence;
    private String emotion;

    private Results results;

    @Builder
    public SentencePredictionDto(String sentence, String emotion, Results results) {
        this.sentence = sentence;
        this.emotion = emotion;
        this.results = results;
    }


    public SentencePrediction toEntity() {
        return new SentencePrediction(sentence, emotion, results);
    }
}
