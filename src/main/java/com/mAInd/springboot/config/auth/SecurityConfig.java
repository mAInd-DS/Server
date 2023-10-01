package com.mAInd.springboot.config.auth;

import com.mAInd.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
//spring security 설정 활성화
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;
//    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
//    private final OAuth2AuthenticationSuccessHandler authenticationSuccessHandler;
//    private final OAuth2AuthenticationFailureHandler authenticationFailureHandler;
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .cors() //cors ON
                .and()
                //h2-console 화면을 사용하기 위해 해당 옵션들을 disable해준다
                .csrf().disable() //csrf off
                .httpBasic().disable() //basic auth off
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Session off
                .and()
                .headers().frameOptions().disable()

                .and()
                    //URL별 권한 관리를 설정하는 옵션의 시작점
                    .authorizeRequests()
                    //권한 관리 대상 지정 옵션 - permitAll: 전체 열람 권한 부여, hasRole: 특정 권한을 가진 사람만 열람 가능
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
//                    .antMatchers("/profiles/**").hasRole(Role.COUNSELOR.name())
                    .antMatchers("/profiles/**").permitAll()
                    .antMatchers("/mypage/**").permitAll()
//                    .antMatchers("/oauth2/**", "/auth/**").permitAll()
//                    .antMatchers("/admin/**").hasRole("ADMIN")
                    //anyRequest: 설정된 값들 이외 나머지 url들
                    //여기서는 나머지 url들은 모두 인증된 사용자들에게만 허용케 함(로그인한 사용자들)
                    .anyRequest().authenticated()

//
//                .and()
//                .formLogin().disable()
//                .oauth2Login()
//                    .authorizationEndpoint() //프론트에서 백엔드로 소셜로그인 요청을 보내는 URI(기본:/oauth2/authorization/{provider})
//                        .baseUri("/oauth2/authorize")
////                        .authorizationRequestRepository(cookieAuthorizationRequestRepository) //authorization 과정에서 기본으로 session을 사용하지만 cookie로 변경하기 위해 설정
//                    .and()
//                    .redirectionEndpoint() //authorization 과정이 끝나면 authorization code와 함께 리다이렉트할 URI
//                        .baseUri("/oauth2/callback/*")
//                    .and()
//                    .userInfoEndpoint() //provider로부터 획득한 유저정보를 다룰 service class 지정
//                        .userService(customOAuth2UserService)
//                    .and()
////                    .successHandler(authenticationSuccessHandler) //oauth2 로그인 성공 시 호출할 handler
////                    .failureHandler(authenticationFailureHandler) //oauth2 로그인 실패 시 호출할 handler

                .and()
                .exceptionHandling() //JWT 다룰 때 생길 exception 처리할 class 지정
//                    .authenticationEntryPoint(jwtAuthenticationEntryPoint) //401, 인증 과정에서 생길 exception 처리
//                    .accessDeniedHandler(jwtAccessDeniedHandler) //403, 인가 과정에서 생길 exception 처리

//                .and()
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                //모든 request에서 JWT를 검사할 filter 추가

                .and()
                    .logout()
                    //로그아웃 기능에 대한 여러 설정의 진입점, 로그아웃 성공 시 / 주소로 이동
                    .logoutSuccessUrl("/")
                .and()
                    //oAuth2 로그인 기능에 대한 여러 설정의 진입점
                    .oauth2Login()
                    //oAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들 담당
                    .userInfoEndpoint()
                    //소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체 등록
                    .userService(customOAuth2UserService);

    }
}
