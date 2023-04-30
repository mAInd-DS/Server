package com.mAInd.springboot.config.auth;

import com.mAInd.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
//spring security 설정 활성화
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                //h2-console 화면을 사용하기 위해 해당 옵션들을 disable해준다
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    //URL별 권한 관리를 설정하는 옵션의 시작점
                    .authorizeRequests()
                    //권한 관리 대상 지정 옵션 - permitAll: 전체 열람 권한 부여, hasRole: 특정 권한을 가진 사람만 열람 가능
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
//                    .antMatchers("/profiles/**").hasRole(Role.COUNSELOR.name())
                    .antMatchers("/profiles/**").permitAll()
                    //anyRequest: 설정된 값들 이외 나머지 url들
                    //여기서는 나머지 url들은 모두 인증된 사용자들에게만 허용케 함(로그인한 사용자들)
                    .anyRequest().authenticated()
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
