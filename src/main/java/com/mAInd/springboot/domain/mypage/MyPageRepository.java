package com.mAInd.springboot.domain.mypage;

import com.mAInd.springboot.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MyPageRepository extends JpaRepository<Users, Long> {


}
