package com.mAInd.springboot.global.oauth;

import com.mAInd.springboot.domain.user.entity.Users;
import com.mAInd.springboot.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional //권한 설정을 위한 어노테이션 추가 (10.09)
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
       log.info("CustomOAuth2UserService.loadUser() 실행 - OAuth2 로그인 요청 진입");

        /**
         * DefaultOAuth2UserService 객체를 생성하여, loadUser(userRequest)를 통해 DefaultOAuth2User 객체를 생성 후 반환
         * DefaultOAuth2UserService의 loadUser()는 소셜 로그인 API의 사용자 정보 제공 URI로 요청을 보내서
         * 사용자 정보를 얻은 후, 이를 통해 DefaultOAuth2User 객체를 생성 후 반환한다.
         * 결과적으로, OAuth2User는 OAuth 서비스에서 가져온 유저 정보를 담고 있는 유저
         */

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);


        /**
         * userRequest에서 registrationId 추출 후 registrationId으로 SocialType 저장
         * http://localhost:8080/oauth2/authorization/google에서 google이 registrationId
         * userNameAttributeName은 이후에 nameAttributeKey로 설정된다.
         */

        //현재 로그인 진행 중인 서비스를 구분(구글/네이버)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        //OAuth2 로그인 진행 시 키가 되는 필드값 (pk)
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        // 소셜 로그인에서 API가 제공하는 userInfo의 Json 값(유저 정보들)
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // socialType에 따라 유저 정보를 통해 OAuthAttributes 객체 생성
        OAuthAttributes extractAttributes = OAuthAttributes.of(userNameAttributeName, attributes);

        Users createdUser = getUser(extractAttributes);

        // DefaultOAuth2User를 구현한 CustomOAuth2User 객체를 생성해서 반환
        return new CustomOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(createdUser.getRoleKey())),
                attributes,
                extractAttributes.getNameAttributeKey(),
                createdUser.getEmail(),
                createdUser.getRole(),
                createdUser.getUserStatus()
        );
    }

    private Users getUser(OAuthAttributes attributes){
        Users findUser = userRepository.findBySocialId(attributes.getOauth2UserInfo().getId()).orElse(null);

        if(findUser == null) {
            return saveUser(attributes);
        }
        return findUser;
    }

    /**
     * OAuthAttributes의 toEntity() 메소드를 통해 빌더로 User 객체 생성 후 반환
     * 생성된 User 객체를 DB에 저장 : socialType, socialId, email, role 값만 있는 상태
     */
    private Users saveUser(OAuthAttributes attributes) {
        Users createdUser = attributes.toEntity(attributes.getOauth2UserInfo());
        return userRepository.save(createdUser);
    }

    /** 로그인 직후 User 권한을 Client로 업데이트 **/
    public void updateAuthorizeClient(String email) {
        Users findUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("이메일에 해당하는 유저가 없습니다."));
        findUser.authorizeClient();
    }

    /** 로그인 직후 refreshToken db값 변경 **/
    public void updateRefreshToken(String email, String refreshToken){
        Users findUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("이메일에 해당하는 유저가 없습니다."));
        findUser.updateRefreshToken(refreshToken);
    }

}
