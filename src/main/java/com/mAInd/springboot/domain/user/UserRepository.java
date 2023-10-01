package com.mAInd.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    //email을 통해 이미 생성된 사용자인지 처음 가입하는 사용자인지 판단
    Optional<Users> findByEmail(String email);

    boolean existsByEmail(String email);
//
//    @Query("SELECT u.refreshToken FROM User u WHERE u.id=:id")
//    String getRefreshTokenById(@Param("id") Long id);
//
//    @Transactional
//    @Modifying
//    @Query("UPDATE User u SET u.refreshToken=:token WHERE u.id=:id")
//    void updateRefreshToken(@Param("id") Long id, @Param("token") String token);
}
