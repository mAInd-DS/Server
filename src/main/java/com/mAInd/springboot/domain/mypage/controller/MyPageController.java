package com.mAInd.springboot.domain.mypage.controller;

import com.mAInd.springboot.domain.mypage.dto.MyInfoResponseDto;
import com.mAInd.springboot.domain.mypage.dto.MyStatusResponseDto;
import com.mAInd.springboot.domain.mypage.service.MyPageService;
import com.mAInd.springboot.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public MyStatusResponseDto getMyStatus() {return myPageService.getMyStatus();}
}
