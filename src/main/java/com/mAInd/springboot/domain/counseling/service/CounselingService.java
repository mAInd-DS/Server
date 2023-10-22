package com.mAInd.springboot.domain.counseling.service;

import com.mAInd.springboot.domain.counseling.dto.*;
import com.mAInd.springboot.domain.counseling.entity.*;
import com.mAInd.springboot.domain.counseling.repository.CounselingRepository;
import com.mAInd.springboot.domain.surveys.repository.SurveysRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CounselingService {

    private final CounselingRepository counselingRepository;
    private final SurveysRepository surveysRepository;

    @Transactional
    public Long save(CounselingSaveRequestDto requestDto){
        return counselingRepository.save(requestDto.toEntity()).getCounseling_id();
    }

    @Transactional
    public Long update(Long survey_id, Long countNum, CounselingUpdateRequestDto requestDto) {
        Counseling counseling = counselingRepository.findBySurveyIdAndCountNum(survey_id, countNum);
        if (counseling == null) {
            throw new IllegalArgumentException("해당 상담이 없습니다. survey_id=" + survey_id + ", countNum=" + countNum);
        }

        List<SentencePrediction> newSentencePredictions = requestDto.toEntityListSP(counseling);
        if (newSentencePredictions == null) {
            throw new IllegalArgumentException("새 SentencePredictions 목록이 null입니다.");
        }
        counseling.clearSentencePredictions();
        counseling.setSentencePredictions(newSentencePredictions);

        List<TotalPercentages> newTotalPercentages = requestDto.toEntityListTP(counseling);
        if (newTotalPercentages == null){
            throw new IllegalArgumentException("새 TotalPercentages 목록이 null입니다.");
        }
        counseling.clearTotalPercentages();;
        counseling.setTotalPercentages(newTotalPercentages);

        List<MergedArray> newMergedArrays = requestDto.toEntityListMA(counseling);
        if (newMergedArrays == null){
            throw new IllegalArgumentException("새 MergedArrays 목록이 null입니다.");
        }
        counseling.clearMergedArrays();;
        counseling.setMergedArrays(newMergedArrays);

        List<EmotionValues> newEmotionValues = requestDto.toEntityListEV(counseling);
        if (newEmotionValues == null){
            throw new IllegalArgumentException("새 EmotionValues 목록이 null입니다.");
        }
        counseling.clearEmotionValues();;
        counseling.setEmotionValues(newEmotionValues);
        counseling.update(requestDto.getOpinion()); //opinion update

        counselingRepository.save(counseling);
        return counseling.getCounseling_id();
    }


    @Transactional
    public CounselingResponseDto findById(Long counseling_id){
        Counseling entity = counselingRepository.findById(counseling_id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 상담이 없습니다. counseling_id=" + counseling_id));
        return new CounselingResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<ClientListResponseDto> findAcceptedSurveysByCounselorId(Long counselorId){
        return surveysRepository.findAcceptedSurveysByCounselorId(counselorId).stream()
                .map(ClientListResponseDto::new)
                .collect(Collectors.toList());
    }


}
