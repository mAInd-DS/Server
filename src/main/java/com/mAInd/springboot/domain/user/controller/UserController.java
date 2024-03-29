package com.mAInd.springboot.domain.user.controller;

import com.mAInd.springboot.domain.user.service.UserService;
import com.mAInd.springboot.domain.user.dto.UserSignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 자체 회원가입
    @PostMapping("/sign-up")
    public String signUp(@RequestBody UserSignUpDto userSignUpDto) throws Exception {
        userService.signUp(userSignUpDto);
        return "회원가입 성공";
    }

    @GetMapping("/jwt-test")
    public String jwtTest() {
        return "jwtTest 요청 성공";
    }

    @GetMapping("/header-test")
    @ResponseBody
    public String currentUserInfo(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/header-test2")
    @ResponseBody
    public String currentUserName(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }




}
