package com.mAInd.springboot.domain.user;

import com.mAInd.springboot.domain.BaseTimeEntity;
import com.mAInd.springboot.domain.profiles.Profiles;
import com.mAInd.springboot.domain.surveys.Gender;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false)
    private String name; //이름

    @Column(nullable = false)
    private String email; //이메일

    @Column
    private String picture; //프로필 이미지

    private String password; //비밀번호

    private String refreshToken; //리프레시 토큰

    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column
    private UserStatus userStatus;

    @Builder
    public Users(String name, String email, String picture, String password, Role role, UserStatus userStatus, String socialId){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.password = password;
        this.userStatus = userStatus;
        this.socialId = socialId;
    }

    public Users update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }


    // 내담자 권한 설정 메소드
    public void authorizeClient(){
        this.role = Role.CLIENT;
    }

    // 상담자 권한 설정 메소드
    public void authorizeCounselor(){
        this.role = Role.COUNSELOR;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

    //비밀번호 암호화 메소드
    public void passwordEncode(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.password);
    }


}
