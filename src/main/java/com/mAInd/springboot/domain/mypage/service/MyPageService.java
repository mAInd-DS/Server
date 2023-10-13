package com.mAInd.springboot.domain.mypage.service;

import com.mAInd.springboot.domain.mypage.dto.MyInfoResponseDto;
import com.mAInd.springboot.domain.surveys.dto.SurveysResponseDto;
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

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MyPageService {

    private final UserRepository userRepository;
    private final SurveysRepository  surveysRepository;

    @Transactional
    public MyInfoResponseDto getMyInfo(){
        Users user = findUser();
        Surveys survey = findSurvey(user);
        return new MyInfoResponseDto(user,survey);
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
        // TODO: 예외처리
        return null;
    }

    public Surveys findSurvey(Users user){
        Long user_id = user.getUser_id();
        Surveys survey = surveysRepository.findLatestSurveyByClientId(user_id);
        return survey;
    }


}

