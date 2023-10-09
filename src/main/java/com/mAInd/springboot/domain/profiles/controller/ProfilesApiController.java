package com.mAInd.springboot.domain.profiles.controller;

import com.mAInd.springboot.domain.profiles.service.ProfilesService;
import com.mAInd.springboot.domain.profiles.dto.ProfilesListResponseDto;
import com.mAInd.springboot.domain.profiles.dto.ProfilesResponseDto;
import com.mAInd.springboot.domain.profiles.dto.ProfilesSaveRequestDto;
import com.mAInd.springboot.domain.profiles.dto.ProfilesUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class ProfilesApiController {
    private final ProfilesService profilesService;

    @PostMapping("/profiles")
    public Long save(@RequestBody ProfilesSaveRequestDto requestDto){
        return profilesService.save(requestDto);
    }

    @PutMapping("/profiles/{profile_id}")
    public Long update(@PathVariable Long profile_id, @RequestBody ProfilesUpdateRequestDto requestDto){
        return profilesService.update(profile_id, requestDto);
    }

    @DeleteMapping("/profiles/{profile_id}")
    public Long delete(@PathVariable Long profile_id) {
        profilesService.delete(profile_id);
        return profile_id;
    }

    @GetMapping("/profiles/{profile_id}")
    public ProfilesResponseDto findById(@PathVariable Long profile_id){
        return profilesService.findById(profile_id);
    }

    @GetMapping("/profiles/list")
    public List<ProfilesListResponseDto> findAll() {
        return profilesService.findAllDesc();
    }
}
