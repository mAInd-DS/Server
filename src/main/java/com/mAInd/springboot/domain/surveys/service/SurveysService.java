package com.mAInd.springboot.domain.surveys.service;

import com.mAInd.springboot.domain.surveys.dto.*;
import com.mAInd.springboot.domain.surveys.entity.ApplyStatus;
import com.mAInd.springboot.domain.surveys.entity.Surveys;
import com.mAInd.springboot.domain.surveys.repository.SurveysRepository;
import com.mAInd.springboot.domain.user.entity.UserStatus;
import com.mAInd.springboot.domain.user.entity.Users;
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
        surveys.update(requestDto.getName(), requestDto.getGender(), requestDto.getEmail(),
                requestDto.getBirth(), requestDto.getPhone(), requestDto.getEducation(), requestDto.getSymptoms(),
                requestDto.getQ_1(), requestDto.getQ_2(), requestDto.getQ_3(),
                requestDto.getQ_4(), requestDto.getQ_5(), requestDto.getQ_6(),
                requestDto.getQ_7(), requestDto.getQ_8());

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


    @Transactional
    public SurveysStatusResponseDto findById2(Long survey_id){
        Surveys entity = surveysRepository.findById(survey_id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 설문지가 없습니다. survey_id=" + survey_id));
        return new SurveysStatusResponseDto(entity);
    }

    @Transactional
    public SurveysResponseDto findById3(Long survey_id){
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

    @Transactional(readOnly = true)
    public List<CounselorApplyListResponseDto> findSurveysByCounselorId(Long counselorId) {
        return surveysRepository.findSurveysByCounselorId(counselorId).stream()
                .map(CounselorApplyListResponseDto::new)
                .collect(Collectors.toList());
    }


    // 상담자가 내담자 설문지 확인 후 상태 변경
    @Transactional
    public Long updateStatus(Long survey_id, SurveyStatusUpdateRequestDto requestDto){
        Surveys surveys = surveysRepository.findById(survey_id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 설문지가 없습니다. survey_id="+survey_id));


        //상담자가 상담을 수락한 경우 내담자의 userStatus 변경 (ON_MATCHING -> AFTER_MATCHING)
        if(requestDto.getApplyStatus() == ApplyStatus.ACCEPT ){
            Users client_id = surveys.getClient_id();
            client_id.setUserStatus(UserStatus.AFTER_MATCHING);
        }

        surveys.updateStatus(requestDto.getApplyStatus(), requestDto.getStatusDate());
        return survey_id;
    }


}
