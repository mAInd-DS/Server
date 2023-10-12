package com.mAInd.springboot.domain.counseling.controller;

import com.mAInd.springboot.domain.counseling.dto.CounselingListResponseDto;
import com.mAInd.springboot.domain.counseling.dto.CounselingSaveRequestDto;
import com.mAInd.springboot.domain.counseling.service.CounselingService;
import com.mAInd.springboot.domain.surveys.dto.CounselorApplyListResponseDto;
import com.mAInd.springboot.domain.surveys.service.SurveysService;
import com.mAInd.springboot.domain.user.entity.Users;
import com.mAInd.springboot.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class CounselingController {

    private final CounselingService counselingService;
    private final UserRepository userRepository;
    private final SurveysService surveysService;

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

}
