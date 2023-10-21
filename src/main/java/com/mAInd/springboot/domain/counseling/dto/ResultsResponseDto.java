package com.mAInd.springboot.domain.counseling.dto;
import com.mAInd.springboot.domain.counseling.entity.Results;
import com.mAInd.springboot.domain.counseling.entity.SentencePrediction;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ResultsResponseDto {

    private Long result_id;
    private Long survey_id;
    private Long countNum;
    private List<SentencePredictionDto> sentencePredictions;

    public ResultsResponseDto(Results entity){
        this.result_id = entity.getResult_id();
        this.survey_id = entity.getResult_id();
        this.countNum = entity.getCountNum();
        this.sentencePredictions = entity.getSentencePredictions().stream()
                .map(sentencePrediction -> new SentencePredictionDto(sentencePrediction.getSentence(), sentencePrediction.getEmotion(), sentencePrediction.getResults()))
                .collect(Collectors.toList());
    }
}
