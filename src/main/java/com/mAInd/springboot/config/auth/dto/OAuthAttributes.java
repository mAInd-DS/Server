package com.mAInd.springboot.config.auth.dto;

import com.mAInd.springboot.config.auth.GoogleOAuth2UserInfo;
import com.mAInd.springboot.config.auth.OAuth2UserInfo;
import com.mAInd.springboot.domain.user.Role;
import com.mAInd.springboot.domain.user.UserStatus;
import com.mAInd.springboot.domain.user.Users;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class OAuthAttributes {
    private String nameAttributeKey; // OAuth2 로그인 진행 시 키가 되는 필드 값, PK와 같은 의미
    private OAuth2UserInfo oauth2UserInfo;

    @Builder
    public OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oauth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oauth2UserInfo = oauth2UserInfo;
    }

    //OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에 값 하나하나를 변환해야 함 -> of() 사용
    public static OAuthAttributes of(String userNameAttributeName, Map<String, Object> attributes){
        return ofGoogle(userNameAttributeName, attributes);
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new GoogleOAuth2UserInfo(attributes))
                .build();
    }

    //toEntity(): User 엔티티 생성
    //OAuthAttributes에서 엔티티를 생성하는 시점은 처음 가입할 때
    //가입할 때의 기본 권한을 GUEST로 줌
    //OAuthAttributes 클래스 생성이 끝나면 같은 패키지에 SessionUser 클래스를 생성함
    public Users toEntity(OAuth2UserInfo oauth2UserInfo) {
        return Users.builder()
                .name(oauth2UserInfo.getName())
                .email(UUID.randomUUID() + "@socialUser.com")
                .picture(oauth2UserInfo.getImageUrl())
                .role(Role.GUEST)
                .userStatus(UserStatus.BEFORE_SURVEY)
                .build();
    }
}
