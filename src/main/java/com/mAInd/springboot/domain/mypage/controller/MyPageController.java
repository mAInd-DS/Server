package com.mAInd.springboot.domain.mypage.controller;

import com.mAInd.springboot.domain.mypage.dto.ClientInfoResponseDto;
import com.mAInd.springboot.domain.mypage.dto.IndividualCounselingListResponseDto;
import com.mAInd.springboot.domain.mypage.dto.MyInfoResponseDto;
import com.mAInd.springboot.domain.mypage.dto.MyStatusResponseDto;
import com.mAInd.springboot.domain.mypage.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/mypage/userInfo")
    public MyInfoResponseDto getMyInfo(){
        return myPageService.getMyInfo();
    }

    @GetMapping("/mypage/status")
    public MyStatusResponseDto getMyStatus() {
        return myPageService.getMyStatus();}

    @GetMapping("/mypage/clientInfo/{survey_id}")
    public ClientInfoResponseDto getClientInfo(@PathVariable Long survey_id){
        return myPageService.getClientInfo(survey_id);
    }

    @GetMapping("/mypage/{survey_id}/counseling/list")
    public List<IndividualCounselingListResponseDto> findIndividualCounselingListBySurveyId(@PathVariable Long survey_id){
        return myPageService.findIndividualCounselingListBySurveyId(survey_id);
    }
}
