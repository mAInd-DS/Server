package com.mAInd.springboot.domain.counseling.entity;

import com.mAInd.springboot.domain.surveys.entity.Surveys;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@Entity
public class Counseling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long counseling_id;

//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "survey_id", nullable = true)
//    @JsonIgnore
//    private Surveys survey_id;

    @Column(nullable = false)
    private Long survey_id;

    @Column(nullable = false)
    private Date date;

    @Column(length=5,nullable = false)
    private Long startHour;

    @Column(length=5,nullable = false)
    private Long startMin;

    @Column(length=5,nullable = false)
    private Long endHour;

    @Column(length=5,nullable = false)
    private Long endMin;

    @Column(length=5,nullable = false)
    private Long countNum;

    @Column(length = 100, nullable = true)
    private String videoName;


    @Builder
    public Counseling(Long survey_id, Date date, Long startHour, Long startMin,
                      Long endHour, Long endMin, Long countNum, String videoName){
        this.survey_id = survey_id;
        this.date = date;
        this.startHour = startHour;
        this.startMin = startMin;
        this.endHour = endHour;
        this.endMin = endMin;
        this.countNum = countNum;
        this.videoName = videoName;
    }


}
