package com.mAInd.springboot.domain.user;

import com.mAInd.springboot.domain.BaseTimeEntity;
import com.mAInd.springboot.domain.profiles.Profiles;
import com.mAInd.springboot.domain.surveys.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    private String password;

    private String refreshToken; // 리프레시 토큰

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column
//    @ColumnDefault("BEFORE_SURVEY")
    private UserStatus userStatus;



//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="profile_id", referencedColumnName = "profile_id")
//    private Profiles profile_id;

    @Builder
    public Users(String name, String email, String picture, Role role, UserStatus userStatus){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.userStatus = userStatus;
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
    public String getUserStatusKey() {return this.userStatus.getKey();}

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

    //비밀번호 암호화 메소드
    public void passwordEncode(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.password);
    }


}
