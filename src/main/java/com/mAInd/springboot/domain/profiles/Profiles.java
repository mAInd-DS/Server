package com.mAInd.springboot.domain.profiles;

import com.mAInd.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //클래스 내 모든 필드의 Getter 메소드 자동생성
@NoArgsConstructor //기본 생성자 자동 추가
@Entity //테이블과 링크될 클래스임을 명시
public class Profiles extends BaseTimeEntity {

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk생성규칙, auto_increment
    private Long counselor_profile_id;

    @Column(length=500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String career;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String education;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String counselor_id;

    @Builder //해당 클래스의 빌더 패턴 클래스를 생성
    public Profiles(String title, String career, String education, String content, String counselor_id){
        this.title = title;
        this.career = career;
        this.education = education;
        this.content = content;
        this.counselor_id = counselor_id;
    }

    public void update(String title, String career, String education, String content){
        this.title = title;
        this.career = career;
        this.education = education;
        this.content = content;
    }
}
