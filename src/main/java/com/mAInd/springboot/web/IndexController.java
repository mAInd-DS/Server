package com.mAInd.springboot.web;

import com.mAInd.springboot.config.auth.LoginUser;
import com.mAInd.springboot.config.auth.dto.SessionUser;
import com.mAInd.springboot.service.profiles.ProfilesService;
import com.mAInd.springboot.service.surveys.SurveysService;
import com.mAInd.springboot.web.dto.ProfilesResponseDto;
import com.mAInd.springboot.web.dto.SurveysResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final ProfilesService profilesService;
//    private final SurveysService surveysService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
       model.addAttribute("profiles", profilesService.findAllDesc());
       //세션에 저장된 값이 있을 때만 model에 userName으로 등록
       if(user != null){
           model.addAttribute("userNameIs", user.getName());
       }
       return "index";
    }

    @GetMapping("/profiles/save")
    public String profilesSave(){
        return "profiles-save";
    }

    @GetMapping("/profiles/update/{profile_id}")
    public String profilesUpdate(@PathVariable Long profile_id, Model model){
        ProfilesResponseDto dto = profilesService.findById(profile_id);
        model.addAttribute("profile", dto);

        return "profiles-update";
    }
//
//
//    @GetMapping("/mypage")
//    public String mypage(Model model, @LoginUser SessionUser user){
//        model.addAttribute("surveys");
//        if(user != null){
//            model.addAttribute("userNameIs", user.getName());
//        }
//        return "survey";
//    }
//
//    @GetMapping("/surveys/save")
//    public String surveysSave(){
//        return "surveys-save";
//    }
//
//    @GetMapping("/surveys/update/{survey_id}")
//    public String surveysUpdate(@PathVariable Long survey_id, Model model){
//        SurveysResponseDto dto = surveysService.findById(survey_id);
//        model.addAttribute("survey", dto);
//        return "surveys-update";
//    }


}
