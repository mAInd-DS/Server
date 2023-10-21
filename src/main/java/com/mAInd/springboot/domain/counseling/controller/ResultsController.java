package com.mAInd.springboot.domain.counseling.controller;

import com.mAInd.springboot.domain.counseling.dto.ResultsResponseDto;
import com.mAInd.springboot.domain.counseling.dto.ResultsSaveRequestDto;
import com.mAInd.springboot.domain.counseling.service.ResultsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class ResultsController {

    private final ResultsService resultsService;

    @PostMapping("/counseling/result")
    public Long save(@RequestBody ResultsSaveRequestDto requestDto){
        return resultsService.save(requestDto);
    }

    @GetMapping("/counseling/result/{result_id}")
    public ResultsResponseDto findById(@PathVariable Long result_id){
        return resultsService.findById(result_id);
    }


}
