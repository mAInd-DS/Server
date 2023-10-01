//package com.mAInd.springboot.config.auth;
//
//import com.google.auth.oauth2.JwtProvider;
//import com.mAInd.springboot.domain.user.UserRepository;
//import com.mAInd.springboot.domain.user.Users;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Map;
//import java.util.Optional;
//
//@Component
//@Transactional
//@RequiredArgsConstructor
//public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
//    private final UserRepository userRepository;
//    private final JwtProvider jwtProvider;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException, ServletException {
//        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//        Long authId = (Long)attributes.get("user_id");
//
//        Optional<Users> findUser = userRepository.findById(authId);
//
//        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(UriList.FRONT_END.getUri());
//
//     }
//}
