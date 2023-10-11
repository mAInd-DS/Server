package com.mAInd.springboot.domain.surveys.controller;

import com.mAInd.springboot.domain.surveys.dto.*;
import com.mAInd.springboot.domain.surveys.service.SurveysService;
import com.mAInd.springboot.domain.user.entity.Users;
import com.mAInd.springboot.domain.user.repository.UserRepository;
import com.mAInd.springboot.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class SurveysApiController {

    private final SurveysService surveysService;

    private final UserService userService;
    private final UserRepository userRepository;
    private Authentication authentication;


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
}
