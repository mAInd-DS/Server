package com.mAInd.springboot.web;

import com.mAInd.springboot.config.auth.LoginUser;
import com.mAInd.springboot.config.auth.dto.SessionUser;
import com.mAInd.springboot.service.profiles.ProfilesService;
import com.mAInd.springboot.web.dto.ProfilesResponseDto;
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
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
       model.addAttribute("profiles", profilesService.findAllDesc());
       //세션에 저장된 값이 있을 때만 model에 userName으로 등록
       if(user != null){
           model.addAttribute("userName", user.getName());
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


}
