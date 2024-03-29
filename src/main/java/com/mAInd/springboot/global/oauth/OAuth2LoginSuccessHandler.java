package com.mAInd.springboot.global.oauth;

import com.mAInd.springboot.global.Jwt.JwtService;
import com.mAInd.springboot.domain.user.entity.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final CustomOAuth2UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Login 성공!");
        try {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

            // User의 Role이 GUEST일 경우 처음 요청한 회원
            if (oAuth2User.getRole() == Role.GUEST) {
                userService.updateAuthorizeClient(oAuth2User.getEmail()); //유저의 Role을 Client로 변경(자동)

                String accessToken = jwtService.createAccessToken(oAuth2User.getEmail());
                response.addHeader(jwtService.getAccessHeader(), "Bearer " + accessToken);

                jwtService.sendAccessAndRefreshToken(response, accessToken, null);

                String url = makeRedirectUrl(accessToken);
                response.sendRedirect(url);
                log.info("redirect url: {}", url);
            } else {
                loginSuccess(response, oAuth2User); //로그인에 성공한 경우 access, refresh 토큰 생성
            }
        } catch(Exception e) {
            throw e;
        }
    }

    private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException{
        String accessToken = jwtService.createAccessToken(oAuth2User.getEmail());
        String refreshToken = jwtService.createRefreshToken();
        response.addHeader(jwtService.getAccessHeader(), "Bearer " + accessToken);
        response.addHeader(jwtService.getRefreshHeader(), "Bearer " + refreshToken);

        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
        jwtService.updateRefreshToken(oAuth2User.getEmail(), refreshToken);

        String url = makeRedirectUrl(accessToken);
        response.sendRedirect(url);
        log.info("redirect url: {}", url);
    }

    private String makeRedirectUrl(String token){
        return UriComponentsBuilder.fromUriString("https://maind.site/oauth2/redirect/" + token)
                .build().toUriString();
    }
}
