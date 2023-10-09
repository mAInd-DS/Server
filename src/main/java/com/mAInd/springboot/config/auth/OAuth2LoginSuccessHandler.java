package com.mAInd.springboot.config.auth;

import com.mAInd.springboot.Jwt.JwtService;
import com.mAInd.springboot.domain.user.Role;
import com.mAInd.springboot.domain.user.UserRepository;
import com.mAInd.springboot.domain.user.Users;
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
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final CustomOAuth2UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Login 성공!");
        try {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
            String email = oAuth2User.getEmail();
//            Optional<Users> existingUserOptional = userRepository.findByEmail(email);

            // User의 Role이 GUEST일 경우 처음 요청한 회원이므로 회원가입 페이지로 리다이렉트,
            if (oAuth2User.getRole() == Role.GUEST) {
                String accessToken = jwtService.createAccessToken(oAuth2User.getEmail());
                String refreshToken = jwtService.createRefreshToken();
                response.addHeader(jwtService.getAccessHeader(), "Bearer " + accessToken);

                jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);

                userService.updateAuthorizeClient(oAuth2User.getEmail());
                userService.updateRefreshToken(oAuth2User.getEmail(), refreshToken);
                log.info(oAuth2User.getEmail());

//                String url = makeRedirectUrl(accessToken);
//                log.info("url: {}", url);
//                response.sendRedirect(url);
//                response.sendRedirect("/");

//                loginSuccess(response, oAuth2User);
//
//                Users findUser = userRepository.findByEmail(oAuth2User.getEmail())
//                                .orElseThrow(() -> new IllegalArgumentException("이메일에 해당하는 유저가 없습니다."));
//                log.info("userInfo:{} {}", findUser.getName(), accessToken);
//                findUser.updateRefreshToken(jwtService.getRefreshHeader());
//                findUser.authorizeClient();
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

        response.sendRedirect("/");
    }

    private String makeRedirectUrl(String token){
        return UriComponentsBuilder.fromUriString("http://localhost:3000/oauth2/redirect" + token)
                .build().toUriString();
    }
}
