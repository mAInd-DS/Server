package com.mAInd.springboot.config.auth;

import com.mAInd.springboot.domain.user.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User extends DefaultOAuth2User {

    private UserStatus userStatus;

    public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities,
                            Map<String, Object> attributes, String nameAttributeKey, UserStatus userStatus){
        super(authorities, attributes, nameAttributeKey);
        this.userStatus = userStatus;

    }
}
