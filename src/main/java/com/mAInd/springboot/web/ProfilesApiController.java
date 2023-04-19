package com.mAInd.springboot.web;

import com.mAInd.springboot.service.profiles.ProfilesService;
import com.mAInd.springboot.web.dto.ProfilesResponseDto;
import com.mAInd.springboot.web.dto.ProfilesSaveRequestDto;
import com.mAInd.springboot.web.dto.ProfilesUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProfilesApiController {

    private final ProfilesService profilesService;

    @PostMapping("/profiles")
    public Long save(@RequestBody ProfilesSaveRequestDto requestDto){
        return profilesService.save(requestDto);
    }

    @PutMapping("/profiles/{id}")
    public Long update(@PathVariable Long id, @RequestBody ProfilesUpdateRequestDto requestDto){
        return profilesService.update(id, requestDto);
    }

    @GetMapping("/profiles/{id}")
    public ProfilesResponseDto findById (@PathVariable Long id){
        return profilesService.findById(id);
    }
}
