package com.mAInd.springboot.domain.surveys.controller;

import com.mAInd.springboot.domain.surveys.service.SurveysService;
import com.mAInd.springboot.domain.surveys.dto.SurveysResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class SurveyController {
    private final SurveysService surveysService;

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
