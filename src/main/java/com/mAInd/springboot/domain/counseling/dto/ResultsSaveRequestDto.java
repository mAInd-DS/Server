//package com.mAInd.springboot.domain.counseling.dto;
//import com.mAInd.springboot.domain.counseling.entity.Results;
//import com.mAInd.springboot.domain.counseling.entity.SentencePrediction;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Getter
//@NoArgsConstructor
//public class ResultsSaveRequestDto {
//
//    private Long survey_id;
//    private Long countNum;
//    private List<SentencePredictionDto> sentence_predictions;
//
//
//    @Builder
//    public ResultsSaveRequestDto(Long survey_id, Long countNum, List<SentencePredictionDto> sentence_predictions){
//        this.survey_id = survey_id;
//        this.countNum = countNum;
//        this.sentence_predictions = sentence_predictions;
//    }
//
//    public Results toEntity() {
//        List<SentencePrediction> sentencePredictionEntities = sentence_predictions.stream()
//                .map(SentencePredictionDto::toEntity)
//                .collect(Collectors.toList());
//
//        Results results = Results.builder()
//                .survey_id(survey_id)
//                .countNum(countNum)
//                .sentencePredictions(sentencePredictionEntities)
//                .build();
//
//        return results;
//    }
//
//
//
//}
