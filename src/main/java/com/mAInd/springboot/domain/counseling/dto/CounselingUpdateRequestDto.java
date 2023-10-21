package com.mAInd.springboot.domain.counseling.dto;

import com.mAInd.springboot.domain.counseling.entity.Counseling;
import com.mAInd.springboot.domain.counseling.entity.SentencePrediction;
import com.mAInd.springboot.domain.counseling.entity.TotalPercentages;
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

    @Builder
    public CounselingUpdateRequestDto(List<SentencePredictionDto> sentence_predictions, List<TotalPercentagesDto> total_percentages) {
        this.sentence_predictions = sentence_predictions;
        this.total_percentages = total_percentages;
    }

    public List<SentencePrediction> toEntityListSP(Counseling counseling) {
        if (sentence_predictions == null) {
            log.info("sentence_predictions == null");
            return Collections.emptyList(); // 빈 리스트 반환 또는 예외 처리
        }

        log.info("Converting sentence predictions to entities:");
        sentence_predictions.forEach(dto -> {
            log.info("Sentence: " + dto.getSentence() + ", Emotion: " + dto.getEmotion());
        });


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
        total_percentages.forEach(dto -> {
            log.info("Emotion: " + dto.getEmotion() + ", Percentage: " + dto.getPercentage());
        });


        List<TotalPercentages> totalPercentageEntities = total_percentages.stream()
                .map(dto -> {
                    TotalPercentages entity = dto.toEntity();
                    entity.setCounseling(counseling);
                    return entity;
                })
                .collect(Collectors.toList());
        return totalPercentageEntities;
    }

}
