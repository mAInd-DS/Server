package com.mAInd.springboot.global.oauth;

import com.mAInd.springboot.domain.user.entity.Role;
import com.mAInd.springboot.domain.user.entity.UserStatus;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;
@Getter
public class CustomOAuth2User extends DefaultOAuth2User {

    private String email;
    private UserStatus userStatus;
    private Role role;

    public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities,
                            Map<String, Object> attributes, String nameAttributeKey, String email, Role role, UserStatus userStatus){
        super(authorities, attributes, nameAttributeKey);
        this.email = email;
        this.role = role;
        this.userStatus = userStatus;
    }

}
