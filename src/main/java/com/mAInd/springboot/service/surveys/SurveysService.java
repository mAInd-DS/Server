package com.mAInd.springboot.service.surveys;

import com.mAInd.springboot.domain.surveys.Surveys;
import com.mAInd.springboot.domain.surveys.SurveysRepository;
import com.mAInd.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SurveysService {

    private final SurveysRepository surveysRepository;

    @Transactional
    public Long save(SurveysSaveRequestDto requestDto){
        return surveysRepository.save(requestDto.toEntity()).getSurvey_id();
    }

    @Transactional
    public Long update(Long survey_id, SurveysUpdateRequestDto requestDto){
        Surveys surveys = surveysRepository.findById(survey_id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 설문지가 없습니다. survey_id="+survey_id));
        surveys.update(requestDto.getName(),requestDto.getGender(), requestDto.getEmail(),
                requestDto.getBirth(), requestDto.getPhone(), requestDto.getEducation(),
                requestDto.getSymptoms(),
                requestDto.getQ_1(), requestDto.getQ_2(), requestDto.getQ_3(),
                requestDto.getQ_4(), requestDto.getQ_5(), requestDto.getQ_6(),
                requestDto.getQ_7(), requestDto.getQ_8(), requestDto.getQ_9(),
                requestDto.getQ_10(), requestDto.getQ_11());

        return survey_id;
    }

    @Transactional
    public void delete(Long survey_id){
        Surveys surveys = surveysRepository.findById(survey_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 설문지가 없습니다. survey_id=" +survey_id));
        surveysRepository.delete(surveys);
    }

    @Transactional
    public SurveysResponseDto findById(Long survey_id){
        Surveys entity = surveysRepository.findById(survey_id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 설문지가 없습니다. survey_id=" + survey_id));
        return new SurveysResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<SurveysListResponseDto> findAllDesc(){
        return surveysRepository.findAllDesc().stream()
                .map(SurveysListResponseDto::new)
                .collect(Collectors.toList());
    }

}
