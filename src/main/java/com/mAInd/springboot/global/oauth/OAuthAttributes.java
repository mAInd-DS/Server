package com.mAInd.springboot.global.oauth;

import com.mAInd.springboot.domain.user.entity.Role;
import com.mAInd.springboot.domain.user.entity.UserStatus;
import com.mAInd.springboot.domain.user.entity.Users;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

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

    /**
     * of메소드로 OAuthAttributes 객체가 생성되어, 유저 정보들이 담긴 OAuth2UserInfo가 소셜 타입별로 주입된 상태
     * OAuth2UserInfo에서 socialId(식별값), nickname, imageUrl을 가져와서 build
     * role은 GUEST로 설정
     */
    public Users toEntity(OAuth2UserInfo oauth2UserInfo) {
        return Users.builder()
                .socialId(oauth2UserInfo.getId())
                .name(oauth2UserInfo.getName())
                .email(oauth2UserInfo.getEmail())
                .picture(oauth2UserInfo.getImageUrl())
                .role(Role.GUEST)
                .userStatus(UserStatus.BEFORE_SURVEY)
                .build();
    }
}
