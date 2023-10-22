package com.mAInd.springboot.domain.mypage.controller;

import com.mAInd.springboot.domain.mypage.dto.*;
import com.mAInd.springboot.domain.mypage.service.MyPageService;
import com.mAInd.springboot.domain.user.entity.Users;
import com.mAInd.springboot.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
public class MyPageController {

    private final MyPageService myPageService;
    private final UserRepository userRepository;

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

    @GetMapping("/mypage/counseling/result/{counseling_id}")
    public CounselingResultResponseDto getResult(@PathVariable Long counseling_id){
        return myPageService.getResult(counseling_id);
    }

    @PutMapping("/mypage/counseling/result/{counseling_id}/opinion")
    public Long updateOpinion(@PathVariable Long counseling_id, @RequestBody ResultOpinionUpdateRequestDto requestDto){
        return myPageService.updateOpinion(counseling_id, requestDto);
    }

    @PutMapping("/mypage/counseling/result/{counseling_id}/status")
    public Long updateStatus(@PathVariable Long counseling_id, @RequestBody ResultOfferStatusUpdateRequestDto requestDto){
        return myPageService.updateResultOfferStatus(counseling_id, requestDto);
    }

    @GetMapping("/mypage/counseling/list")
    public List<ClientCounselingListResponseDto> findCounselingForClient(){
        return myPageService.findCounselingForClient();
    }


}
