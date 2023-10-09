package com.mAInd.springboot.domain.mypage.service;

import com.mAInd.springboot.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyPageService {

    private final UserRepository userRepository;


}

