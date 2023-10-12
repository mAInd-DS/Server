package com.mAInd.springboot.domain.counseling.service;

import com.mAInd.springboot.domain.counseling.dto.CounselingListResponseDto;
import com.mAInd.springboot.domain.counseling.dto.CounselingResponseDto;
import com.mAInd.springboot.domain.counseling.dto.CounselingSaveRequestDto;
import com.mAInd.springboot.domain.counseling.entity.Counseling;
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
    public CounselingResponseDto findById(Long counseling_id){
        Counseling entity = counselingRepository.findById(counseling_id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 상담이 없습니다. counseling_id=" + counseling_id));
        return new CounselingResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<CounselingListResponseDto> findAcceptedSurveysByCounselorId(Long counselorId){
        return surveysRepository.findAcceptedSurveysByCounselorId(counselorId).stream()
                .map(CounselingListResponseDto::new)
                .collect(Collectors.toList());
    }

}
