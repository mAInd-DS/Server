package com.mAInd.springboot.domain.user.service;

import com.mAInd.springboot.domain.user.dto.UserSignUpDto;
import com.mAInd.springboot.domain.user.entity.Role;
import com.mAInd.springboot.domain.user.entity.UserStatus;
import com.mAInd.springboot.domain.user.entity.Users;
import com.mAInd.springboot.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(UserSignUpDto userSignUpDto) throws Exception {

        if (userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        Users user = Users.builder()
                .email(userSignUpDto.getEmail())
                .password(userSignUpDto.getPassword())
                .name(userSignUpDto.getName())
                .role(Role.GUEST)
                .userStatus(UserStatus.BEFORE_SURVEY)
                .build();

        user.passwordEncode(passwordEncoder);
        userRepository.save(user);
    }


}

