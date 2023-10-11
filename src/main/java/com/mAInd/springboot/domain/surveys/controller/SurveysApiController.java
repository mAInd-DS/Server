package com.mAInd.springboot.domain.surveys.controller;

import com.mAInd.springboot.domain.surveys.dto.*;
import com.mAInd.springboot.domain.surveys.entity.Surveys;
import com.mAInd.springboot.domain.surveys.service.SurveysService;
import com.mAInd.springboot.domain.user.entity.Users;
import com.mAInd.springboot.domain.user.repository.UserRepository;
import com.mAInd.springboot.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class SurveysApiController {

    private final SurveysService surveysService;
    private final UserRepository userRepository;


    @PostMapping("/mypage/surveys")
    public Long save(@RequestBody SurveysSaveRequestDto requestDto){

        // 설문지 작성 시 유저 정보 저장
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Optional<Users> findUser = userRepository.findByEmail(username);
        if(findUser.isPresent()){
            Users user = findUser.get();
            requestDto.setUserInfo(user);
            return surveysService.save(requestDto);
        }
        return surveysService.save(requestDto);
    }

    @PutMapping("/mypage/surveys/{survey_id}")
    public Long update(@PathVariable Long survey_id, @RequestBody SurveysUpdateRequestDto requestDto){
        return surveysService.update(survey_id, requestDto);
    }

    @DeleteMapping("/mypage/surveys/{survey_id}")
    public Long delete(@PathVariable Long survey_id){
        surveysService.delete(survey_id);
        return survey_id;
    }

    @GetMapping("/mypage/surveys/{survey_id}")
    public SurveysResponseDto findById(@PathVariable Long survey_id){
        return surveysService.findById(survey_id);
    }

    @GetMapping("/mypage/surveys/list")
    public List<SurveysListResponseDto> findAll(){
        return surveysService.findAllDesc();
    }

    @GetMapping("/mypage/surveys/{survey_id}/status")
    public SurveysStatusResponseDto findById2(@PathVariable Long survey_id){
        return surveysService.findById2(survey_id);
    }


    //상담자가 가진 모든 내담자의 설문지 리스트 조회 api
    @GetMapping("/counseling/apply/list")
    public List<CounselorApplyListResponseDto> findSurveysForCounselor(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            String username = userDetails.getUsername();
            Optional<Users> findUser = userRepository.findByEmail(username);
            if (findUser.isPresent()) {
                Users user = findUser.get();
                Long counselorId = user.getUser_id();
                return surveysService.findSurveysByCounselorId(counselorId);
            }
        }
        return Collections.emptyList();
    }

    //상담자가 가진 내담자의 설문지 조회 api
    @GetMapping("/counseling/apply/{survey_id}")
    public SurveysResponseDto findById3(@PathVariable Long survey_id){
        return surveysService.findById3(survey_id);
    }

    // 상담자가 내담자 설문지 확인 후 상태 변경
    @PutMapping("/counseling/apply/{survey_id}/status")
    public Long updateStatus(@PathVariable Long survey_id, @RequestBody SurveyStatusUpdateRequestDto requestDto){
        return surveysService.updateStatus(survey_id, requestDto);
    }

}
