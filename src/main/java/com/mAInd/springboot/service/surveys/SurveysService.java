package com.mAInd.springboot.service.surveys;

import com.mAInd.springboot.domain.surveys.Surveys;
import com.mAInd.springboot.domain.surveys.SurveysRepository;
import com.mAInd.springboot.web.dto.SurveysResponseDto;
import com.mAInd.springboot.web.dto.SurveysSaveRequestDto;
import com.mAInd.springboot.web.dto.SurveysUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SurveysService {

    private final SurveysRepository surveysRepository;

    @Transactional
    public Long save(SurveysSaveRequestDto requestDto){
        return surveysRepository.save(requestDto.toEntity()).getSurvey_id();
    }

    @Transactional
    public Long update(Long id, SurveysUpdateRequestDto requestDto){
        Surveys surveys = surveysRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다"+id));
        surveys.update(requestDto.getName(),requestDto.getGender(), requestDto.getEmail(),
                requestDto.getBirth(), requestDto.getPhone(), requestDto.getEducation(),
                requestDto.getQ_1(), requestDto.getQ_2(), requestDto.getQ_3(),
                requestDto.getQ_4(), requestDto.getQ_5(), requestDto.getQ_6(),
                requestDto.getQ_7(), requestDto.getQ_8(), requestDto.getQ_9(),
                requestDto.getQ_10(), requestDto.getQ_11(), requestDto.getQ_12(),
                requestDto.getQ_13(), requestDto.getQ_14());

        return id;
    }

    @Transactional
    public void delete(Long id){
        Surveys surveys = surveysRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" +id));
        surveysRepository.delete(surveys);
    }

    public SurveysResponseDto findById(Long id){
        Surveys entity = surveysRepository.findById(id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 설문지가 없습니다." + id));
        return new SurveysResponseDto(entity);
    }


}
