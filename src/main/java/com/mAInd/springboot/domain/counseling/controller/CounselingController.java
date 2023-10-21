package com.mAInd.springboot.domain.counseling.controller;

import com.mAInd.springboot.domain.counseling.dto.CounselingListResponseDto;
import com.mAInd.springboot.domain.counseling.dto.CounselingResponseDto;
import com.mAInd.springboot.domain.counseling.dto.CounselingSaveRequestDto;
import com.mAInd.springboot.domain.counseling.dto.CounselingUpdateRequestDto;
import com.mAInd.springboot.domain.counseling.service.CounselingService;
import com.mAInd.springboot.domain.surveys.dto.SurveysResponseDto;
import com.mAInd.springboot.domain.surveys.service.SurveysService;
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
public class CounselingController {

    private final CounselingService counselingService;
    private final UserRepository userRepository;


    @PostMapping("/counseling/upload")
    public Long save(@RequestBody CounselingSaveRequestDto requestDto){
        return counselingService.save(requestDto);
    }

    //상담영상 업로드 부분
    // SurveysApiController의 /counseling/apply/list와의 차이점: applyStatus가 'accept'인 것들만 반환
    @GetMapping("/counseling/list")
    public List<CounselingListResponseDto> findApplyForCounselor(@AuthenticationPrincipal UserDetails userDetails){
        if(userDetails != null){
            String username = userDetails.getUsername();
            Optional<Users> findUser = userRepository.findByEmail(username);
            if (findUser.isPresent()) {
                Users user = findUser.get();
                Long counselorId = user.getUser_id();
                return counselingService.findAcceptedSurveysByCounselorId(counselorId);
            }
        }
        return Collections.emptyList();
    }


    @PutMapping("/counseling/{survey_id}/{countNum}")
    public Long update(@PathVariable Long survey_id, @PathVariable Long countNum, @RequestBody CounselingUpdateRequestDto requestDto){
        log.info("Received request: " + requestDto.toString()); // 요청을 로그로 출력
        return counselingService.update(survey_id, countNum, requestDto);
    }


    @GetMapping("/counseling/{counseling_id}")
    public CounselingResponseDto findById(@PathVariable Long counseling_id){
        return counselingService.findById(counseling_id);
    }


}
