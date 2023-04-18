package com.mAInd.springboot.domain.profiles;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfilesRepositoryTest {

    @Autowired
    ProfilesRepository profilesRepository;

    @After
    public void cleanup(){
        profilesRepository.deleteAll();
    }

    @Test
    public void 프로필저장_불러오기(){
        //given
        String title = "테스트 - 제목";
        String career = "테스트 - 경력";
        String education = "테스트 - 학력";
        String content = "테스트 - 본문";

        profilesRepository.save(Profiles.builder() //테이블 profiles에 insert/update 쿼리를 실행
                .title(title)
                .career(career)
                .education(education)
                .content(content)
                .author("abc@gmail.com")
                .build());

        //when
        List<Profiles> profilesList = profilesRepository.findAll(); //테이블 모든 데이터를 조회

        //then
        Profiles profiles = profilesList.get(0);
        assertThat(profiles.getTitle()).isEqualTo(title);
        assertThat(profiles.getCareer()).isEqualTo(career);
        assertThat(profiles.getEducation()).isEqualTo(education);
        assertThat(profiles.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2023,4,10,0,0,0);
        profilesRepository.save(Profiles.builder()
                .title("title")
                .career("career")
                .education("education")
                .content("content")
                .author("author")
                .build());

        //when
        List<Profiles> profilesList = profilesRepository.findAll();

        //then
        Profiles profiles = profilesList.get(0);

        System.out.println(">>>>>>>>> createDate="+profiles.getCreatedDate()+", " +
                "modifiedDate="+profiles.getModifiedDate());
        assertThat(profiles.getCreatedDate()).isAfter(now);
        assertThat(profiles.getModifiedDate()).isAfter(now);
    }
}
