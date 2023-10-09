package com.mAInd.springboot.domain.surveys.controller;

import com.mAInd.springboot.domain.surveys.dto.*;
import com.mAInd.springboot.domain.surveys.service.SurveysService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class SurveysApiController {

    private final SurveysService surveysService;

    @PostMapping("/mypage/surveys")
    public Long save(@RequestBody SurveysSaveRequestDto requestDto){
        return surveysService.save(requestDto);
    }

    @PutMapping("/mypage/surveys/{survey_id}")
    public Long update(@PathVariable Long survey_id, @RequestBody SurveysUpdateRequestDto requestDto){
        return surveysService.update(survey_id, requestDto);
    }

    @DeleteMapping("/mypage/surveys/{survey_id}")
    public Long delete(@PathVariable Long survey_id){
        surveysService.delete(survey_id);
        return survey_id;
    }

    @GetMapping("/mypage/surveys/{survey_id}")
    public SurveysResponseDto findById(@PathVariable Long survey_id){
        return surveysService.findById(survey_id);
    }

    @GetMapping("/mypage/surveys/list")
    public List<SurveysListResponseDto> findAll(){
        return surveysService.findAllDesc();
    }

    @GetMapping("/mypage/surveys/{survey_id}/status")
    public SurveysStatusResponseDto findById2(@PathVariable Long survey_id){
        return surveysService.findById2(survey_id);
    }
}
