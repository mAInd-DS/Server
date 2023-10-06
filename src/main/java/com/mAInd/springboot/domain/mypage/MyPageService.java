package com.mAInd.springboot.domain.mypage;

import com.mAInd.springboot.domain.user.UserRepository;
import com.mAInd.springboot.domain.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyPageService {

    private final UserRepository userRepository;


}

