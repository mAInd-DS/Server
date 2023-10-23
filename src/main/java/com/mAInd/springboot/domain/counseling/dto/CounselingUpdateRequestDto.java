package com.mAInd.springboot.domain.counseling.dto;

import com.mAInd.springboot.domain.counseling.dto.List.EmotionValuesDto;
import com.mAInd.springboot.domain.counseling.dto.List.MergedArrayDto;
import com.mAInd.springboot.domain.counseling.dto.List.SentencePredictionDto;
import com.mAInd.springboot.domain.counseling.dto.List.TotalPercentagesDto;
import com.mAInd.springboot.domain.counseling.entity.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
@NoArgsConstructor
public class CounselingUpdateRequestDto {
    private List<SentencePredictionDto> sentence_predictions;
    private List<TotalPercentagesDto> total_percentages;
    private List<MergedArrayDto> merged_array;

    private List<EmotionValuesDto> emotion_values;
    private String opinion;

    private Long survey_id;
    private Long countNum;

    @Builder
    public CounselingUpdateRequestDto(List<SentencePredictionDto> sentence_predictions, List<TotalPercentagesDto> total_percentages,
                                      List<MergedArrayDto> merged_array, List<EmotionValuesDto> emotion_values,
                                      String opinion,Long survey_id, Long countNum) {
        this.sentence_predictions = sentence_predictions;
        this.total_percentages = total_percentages;
        this.merged_array = merged_array;
        this.emotion_values = emotion_values;
        this.opinion = opinion;
        this.survey_id = survey_id;
        this.countNum = countNum;
    }

    public List<SentencePrediction> toEntityListSP(Counseling counseling) {
        if (sentence_predictions == null) {
            log.info("sentence_predictions == null");
            return Collections.emptyList(); // 빈 리스트 반환 또는 예외 처리
        }

        log.info("Converting sentence predictions to entities:");
//        sentence_predictions.forEach(dto -> {
//            log.info("Sentence: " + dto.getSentence() + ", Emotion: " + dto.getEmotion());
//        });


        List<SentencePrediction> sentencePredictionEntities = sentence_predictions.stream()
                .map(dto -> {
                    SentencePrediction entity = dto.toEntity();
                    entity.setCounseling(counseling);
                    return entity;
                })
                .collect(Collectors.toList());
        return sentencePredictionEntities;
    }

    public List<TotalPercentages> toEntityListTP(Counseling counseling) {
        if (total_percentages == null) {
            log.info("total_percentages == null");
            return Collections.emptyList(); // 빈 리스트 반환 또는 예외 처리
        }

        log.info("Converting total_percentages to entities:");
//        total_percentages.forEach(dto -> {
//            log.info("Emotion: " + dto.getEmotion() + ", Percentage: " + dto.getPercentage());
//        });


        List<TotalPercentages> totalPercentageEntities = total_percentages.stream()
                .map(dto -> {
                    TotalPercentages entity = dto.toEntity();
                    entity.setCounseling(counseling);
                    return entity;
                })
                .collect(Collectors.toList());
        return totalPercentageEntities;
    }

    public List<MergedArray> toEntityListMA(Counseling counseling) {
        if (merged_array == null) {
            log.info("merged_array == null");
            return Collections.emptyList(); // 빈 리스트 반환 또는 예외 처리
        }

        log.info("Converting merged_array to entities:");
//        merged_array.forEach(dto -> {
//            log.info("speakers: " + dto.getSpeakers() + ", Sentences: " + dto.getSentence());
//        });


        List<MergedArray> mergedArraysEntities = merged_array.stream()
                .map(dto -> {
                    MergedArray entity = dto.toEntity();
                    entity.setCounseling(counseling);
                    return entity;
                })
                .collect(Collectors.toList());
        return mergedArraysEntities;
    }

    public List<EmotionValues> toEntityListEV(Counseling counseling) {
        if (emotion_values == null) {
            log.info("emotion_values == null");
            return Collections.emptyList(); // 빈 리스트 반환 또는 예외 처리
        }

        List<EmotionValues> emotionValuesEntities = emotion_values.stream()
                .map(dto -> {
                    EmotionValues entity = dto.toEntity();
                    entity.setCounseling(counseling);
                    return entity;
                })
                .collect(Collectors.toList());
        return emotionValuesEntities;
    }
}
