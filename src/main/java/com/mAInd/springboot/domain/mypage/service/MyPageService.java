package com.mAInd.springboot.domain.mypage.service;

import com.mAInd.springboot.domain.counseling.entity.Counseling;
import com.mAInd.springboot.domain.counseling.repository.CounselingRepository;
import com.mAInd.springboot.domain.mypage.dto.*;
import com.mAInd.springboot.domain.surveys.entity.Surveys;
import com.mAInd.springboot.domain.surveys.repository.SurveysRepository;
import com.mAInd.springboot.domain.user.entity.Users;
import com.mAInd.springboot.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class MyPageService {

    private final UserRepository userRepository;
    private final SurveysRepository  surveysRepository;
    private final CounselingRepository counselingRepository;

    @Transactional
    public MyInfoResponseDto getMyInfo(){
        Users user = findUser();
        Surveys survey = findSurvey(user);
        return new MyInfoResponseDto(user,survey);
    }

    @Transactional
    public MyStatusResponseDto getMyStatus(){
        Users user = findUser();
        Surveys survey = findSurvey(user);
        return new MyStatusResponseDto(user, survey);
    }

    @Transactional
    public ClientInfoResponseDto getClientInfo(Long survey_id) {
        Surveys entity = surveysRepository.findById(survey_id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 설문지가 없습니다. survey_id=" + survey_id));
        return new ClientInfoResponseDto(entity);
    }

    @Transactional
    public List<IndividualCounselingListResponseDto> findIndividualCounselingListBySurveyId(Long surveyId){
        List<Counseling> counselingEntities = counselingRepository.findBySurveyId(surveyId);

        Surveys surveyEntity = surveysRepository.findById(surveyId)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 설문지가 없습니다. survey_id=" + surveyId));

        return counselingEntities.stream()
                .map(counselingEntity -> new IndividualCounselingListResponseDto(counselingEntity, surveyEntity))
                .collect(Collectors.toList());
    }


    @Transactional
    public CounselingResultResponseDto getResult(Long counseling_id){
        Counseling counselingEntity = counselingRepository.findById(counseling_id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 상담 내역이 없습니다. counseling_id=" + counseling_id));

        Surveys surveyEntity = surveysRepository.findById(counselingEntity.getSurveyId())
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 설문지가 없습니다. survey_id=" + counselingEntity.getSurveyId()));

        return new CounselingResultResponseDto(counselingEntity, surveyEntity);
    }

    @Transactional
    public Long updateOpinion(Long counseling_id, ResultOpinionUpdateRequestDto requestDto){
        Counseling counseling = counselingRepository.findById(counseling_id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 상담 내역이 없습니다. counseling_id=" + counseling_id));
        counseling.updateOpinion(requestDto.getOpinion());
        return counseling_id;
    }

    @Transactional
    public Long updateResultOfferStatus(Long counseling_id, ResultOfferStatusUpdateRequestDto requestDto) {
        Counseling counseling = counselingRepository.findById(counseling_id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 상담 내역이 없습니다. counseling_id=" + counseling_id));
        counseling.updateStatus(requestDto.getResultOfferStatus());
        return counseling_id;
    }

    @Transactional(readOnly = true)
    public List<ClientCounselingListResponseDto> findCounselingForClient() {
        Users user = findUser();
        Surveys survey = findSurvey(user);
        Long surveyId = survey.getSurvey_id();

        List<Counseling> counselingEntities = counselingRepository.findBySurveyId(surveyId);
        List<ClientCounselingListResponseDto> responseDtos = counselingEntities.stream()
                .map(counselingEntity -> new ClientCounselingListResponseDto(counselingEntity, surveysRepository))
                .collect(Collectors.toList());

        return responseDtos;
    }


    public Users findUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            String username = ((User) authentication.getPrincipal()).getUsername();
            Optional<Users> findUser = userRepository.findByEmail(username);
            if (findUser.isPresent()) {
                Users user = findUser.get();
                return user;
            }
        }
        throw new RuntimeException("사용자를 찾을 수 없습니다.");
    }

    public Surveys findSurvey(Users user){
        Long user_id = user.getUser_id();
        Surveys survey = surveysRepository.findLatestSurveyByClientId(user_id);
        return survey;
    }


}

