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
public class SurveyController {
    private final SurveysService surveysService;

    @GetMapping("/mypage")
    public String mypage(Model model, @LoginUser SessionUser user){
        model.addAttribute("surveys", surveysService.findAllDesc());
        if(user != null){
            model.addAttribute("userNameIs", user.getName());
        }
        return "survey";
    }

    @GetMapping("/mypage/surveys/save")
    public String surveysSave(){
        return "surveys-save";
    }

    @GetMapping("/mypage/surveys/update/{survey_id}")
    public String surveysUpdate(@PathVariable Long survey_id, Model model){
        SurveysResponseDto dto = surveysService.findById(survey_id);
        model.addAttribute("survey", dto);
        return "surveys-update";
    }
}
