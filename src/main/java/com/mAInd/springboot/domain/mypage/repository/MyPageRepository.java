package com.mAInd.springboot.domain.mypage.repository;

import com.mAInd.springboot.domain.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPageRepository extends JpaRepository<Users, Long> {


}
