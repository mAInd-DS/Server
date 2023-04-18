package com.mAInd.springboot.web;

import com.mAInd.springboot.domain.profiles.Profiles;
import com.mAInd.springboot.domain.profiles.ProfilesRepository;
import com.mAInd.springboot.web.dto.ProfilesSaveRequestDto;
import com.mAInd.springboot.web.dto.ProfilesUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfilesApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProfilesRepository profilesRepository;

    @After
    public void tearDown() throws Exception{
        profilesRepository.deleteAll();
    }

    @Test
    public void Profiles_등록된다() throws Exception{
        //given
        String title = "title";
        String career = "career";
        String education = "education";
        String content = "content";
        ProfilesSaveRequestDto requestDto = ProfilesSaveRequestDto.builder()
                .title(title)
                .career(career)
                .education(education)
                .content(content)
                .build();

        String url = "http://localhost:" + port + "/api/v1/profiles";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Profiles> all = profilesRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getCareer()).isEqualTo(career);
        assertThat(all.get(0).getEducation()).isEqualTo(education);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Profiles_수정된다() throws Exception{
        //given
        Profiles savedProfiles = profilesRepository.save(Profiles.builder()
                .title("title")
                .career("career")
                .education("education")
                .content("content")
                .author("author")
                .build());

        Long updateId = savedProfiles.getCounselor_profile_id();
        String expectedTitle = "title2";
        String expectedCareer = "Career2";
        String expectedEducation = "Education2";
        String expectedContent = "Content2";

        ProfilesUpdateRequestDto requestDto =
                ProfilesUpdateRequestDto.builder()
                        .title(expectedTitle)
                        .career(expectedCareer)
                        .education(expectedEducation)
                        .content(expectedContent)
                        .build();

        String url = "http://localhost:" + port + "/api/v1/profiles/" + updateId;

        HttpEntity<ProfilesUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long>  responseEntity = restTemplate.
                exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Profiles> all = profilesRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getCareer()).isEqualTo(expectedCareer);
        assertThat(all.get(0).getEducation()).isEqualTo(expectedEducation);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}
