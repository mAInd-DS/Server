package com.mAInd.springboot.web;

import com.mAInd.springboot.service.profiles.ProfilesService;
import com.mAInd.springboot.web.dto.ProfilesListResponseDto;
import com.mAInd.springboot.web.dto.ProfilesResponseDto;
import com.mAInd.springboot.web.dto.ProfilesSaveRequestDto;
import com.mAInd.springboot.web.dto.ProfilesUpdateRequestDto;
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
