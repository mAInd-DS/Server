package com.mAInd.springboot.global.web;

import com.mAInd.springboot.domain.profiles.service.ProfilesService;
import com.mAInd.springboot.domain.profiles.dto.ProfilesResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
@Slf4j
public class IndexController {
    private final ProfilesService profilesService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

//    @GetMapping("/profiles/save")
//    public String profilesSave(){
//        return "profiles-save";
//    }
//
//    @GetMapping("/profiles/update/{profile_id}")
//    public String profilesUpdate(@PathVariable Long profile_id, Model model){
//        ProfilesResponseDto dto = profilesService.findById(profile_id);
//        model.addAttribute("profile", dto);
//
//        return "profiles-update";
//    }

    @GetMapping("/log")
    public void log(){
        log.trace("trace message");
        log.debug("debug message");
        log.info("info message"); // default
        log.warn("warn message");
        log.error("error message");
    }
}
