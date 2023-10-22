package com.mAInd.springboot.domain.user.repository;

import com.mAInd.springboot.domain.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    //email을 통해 이미 생성된 사용자인지 처음 가입하는 사용자인지 판단
    Optional<Users> findByEmail(String email);
    Optional<Users> findByRefreshToken(String refreshToken);
    Optional<Users> findBySocialId(String socialId);


}
