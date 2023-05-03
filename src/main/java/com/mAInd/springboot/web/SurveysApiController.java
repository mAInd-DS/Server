package com.mAInd.springboot.web;

import com.mAInd.springboot.service.surveys.SurveysService;
import com.mAInd.springboot.web.dto.SurveysResponseDto;
import com.mAInd.springboot.web.dto.SurveysSaveRequestDto;
import com.mAInd.springboot.web.dto.SurveysUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SurveysApiController {

    private final SurveysService surveysService;

    @PostMapping("/surveys")
    public Long save(@RequestBody SurveysSaveRequestDto requestDto){
        return surveysService.save(requestDto);
    }

    @PutMapping("/surveys/{id}")
    public Long update(@PathVariable Long id, @RequestBody SurveysUpdateRequestDto requestDto){
        return surveysService.update(id, requestDto);
    }

    @DeleteMapping("/surveys/{id}")
    public Long delete(@PathVariable Long id){
        surveysService.delete(id);
        return id;
    }

    @GetMapping("/surveys/{id}")
    public SurveysResponseDto findById(@PathVariable Long id){
        return surveysService.findById(id);
    }
}
