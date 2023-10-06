package com.mAInd.springboot.domain.surveys;

import com.mAInd.springboot.domain.BaseTimeEntity;
import com.mAInd.springboot.domain.surveys.Gender;
import com.mAInd.springboot.domain.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@Entity
public class Surveys extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long survey_id;

    @Column(length=50, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 5)
    private Gender gender;

    @Column(length=50, nullable = false)
    private String email;

    @Column(nullable = false)
    private Date birth;

    @Column(length=50, nullable = false)
    private String phone;

    @Column(length = 100, nullable = false)
    private String education;

    @ElementCollection
    @Column
    private List<String> symptoms;

    @Column(length = 1000, nullable = false)
    private String q_1;
    private String q_2;
    private String q_3;
    private String q_4;
    private String q_5;
    private String q_6;
    private String q_7;
    private String q_8;
    private String q_9;
    private String q_10;
    private String q_11;

//    @Column(nullable = true)
//    private Long client_id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "client_id", nullable = true)
    @JsonIgnore
    private Users client_id;

    @Column(nullable = true)
    private Long counselor_id;


    @Builder
    public Surveys(String name, Gender gender, String email, Date birth,
                   String phone, String education, List<String> symptoms,
                   String q_1, String q_2,  String q_3, String q_4, String q_5, String q_6,
                   String q_7, String q_8, String q_9, String q_10, String q_11, Users client_id, Long counselor_id) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.birth = birth;
        this.phone = phone;
        this.education = education;
        this.symptoms = symptoms;
        this.q_1 = q_1;
        this.q_2 = q_2;
        this.q_3 = q_3;
        this.q_4 = q_4;
        this.q_5 = q_5;
        this.q_6 = q_6;
        this.q_7 = q_7;
        this.q_8 = q_8;
        this.q_9 = q_9;
        this.q_10 = q_10;
        this.q_11 = q_11;
        this.client_id = client_id;
        this.counselor_id = counselor_id;
    }

    public void update(String name, Gender gender, String email, Date birth,
                       String phone, String education, List<String> symptoms, String q_1, String q_2,
                       String q_3, String q_4, String q_5, String q_6, String q_7,
                       String q_8, String q_9, String q_10, String q_11){
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.birth = birth;
        this.phone = phone;
        this.education = education;
        this.symptoms = symptoms;
        this.q_1 = q_1;
        this.q_2 = q_2;
        this.q_3 = q_3;
        this.q_4 = q_4;
        this.q_5 = q_5;
        this.q_6 = q_6;
        this.q_7 = q_7;
        this.q_8 = q_8;
        this.q_9 = q_9;
        this.q_10 = q_10;
        this.q_11 = q_11;
    }

}
