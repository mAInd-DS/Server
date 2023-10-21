package com.mAInd.springboot.domain.counseling.dto;

import com.mAInd.springboot.domain.counseling.entity.Counseling;
import com.mAInd.springboot.domain.counseling.entity.SentencePrediction;
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

    @Builder
    public CounselingUpdateRequestDto(List<SentencePredictionDto> sentence_predictions) {
        this.sentence_predictions = sentence_predictions;
    }

    public List<SentencePrediction> toEntityList(Counseling counseling) {
        if (sentence_predictions == null) {
            log.info("sentence_predictions == null");
            return Collections.emptyList(); // 빈 리스트 반환 또는 예외 처리
        }

        log.info("Converting sentence predictions to entities:");
        sentence_predictions.forEach(dto -> {
            log.info("Sentence: " + dto.getSentence() + ", Emotion: " + dto.getEmotion());
        });

//        return sentence_predictions.stream()
//                .map(SentencePredictionDto::toEntity)
//                .collect(Collectors.toList());


        List<SentencePrediction> sentencePredictionEntities = sentence_predictions.stream()
                .map(dto -> {
                    SentencePrediction entity = dto.toEntity();
                    entity.setCounseling(counseling);
                    return entity;
                })
                .collect(Collectors.toList());
        return sentencePredictionEntities;
    }

}
