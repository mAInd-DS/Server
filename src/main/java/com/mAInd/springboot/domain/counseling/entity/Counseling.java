package com.mAInd.springboot.domain.counseling.entity;

import com.mAInd.springboot.domain.surveys.entity.Surveys;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.LAZY;


@Slf4j
@Getter
@NoArgsConstructor
@Entity
public class Counseling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long counseling_id;

    @Column(nullable = false)
    private Long surveyId;

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

    @JsonIgnore
    @OneToMany(mappedBy = "counseling", cascade = CascadeType.ALL)
    @Column
    private List<SentencePrediction> sentencePredictions = new ArrayList<SentencePrediction>();

    @JsonIgnore
    @OneToMany(mappedBy = "counseling", cascade = CascadeType.ALL)
    @Column
    private List<TotalPercentages> totalPercentages = new ArrayList<TotalPercentages>();


    @Builder
    public Counseling(Long surveyId, Date date, Long startHour, Long startMin,
                      Long endHour, Long endMin, Long countNum,
                      List<SentencePrediction> sentencePredictions,
                      List<TotalPercentages> totalPercentages){
        this.surveyId = surveyId;
        this.date = date;
        this.startHour = startHour;
        this.startMin = startMin;
        this.endHour = endHour;
        this.endMin = endMin;
        this.countNum = countNum;
        this.sentencePredictions = sentencePredictions;
        this.totalPercentages = totalPercentages;
    }

    public void clearSentencePredictions() {
        if (this.sentencePredictions != null) {
            log.info("clear sentence predictions");
            this.sentencePredictions.clear();
        }
    }

    public void setSentencePredictions(List<SentencePrediction> newSentencePredictions) {
        if (newSentencePredictions == null) {
            this.sentencePredictions = new ArrayList<>(); // 빈 리스트 생성
            log.info("Created a new sentencePredictions list");
        } else {
            this.sentencePredictions = newSentencePredictions; // 새로운 리스트 설정
            log.info("Set sentencePredictions to a new list");
        }
    }

    public void clearTotalPercentages(){
        if(this.totalPercentages != null){
            log.info("clear total percentages");
            this.totalPercentages.clear();
        }
    }

    public void setTotalPercentages(List<TotalPercentages> newTotalPercentages){
        if(newTotalPercentages == null){
            this.totalPercentages = new ArrayList<>();
            log.info("Created a new totalPercentages list");
        }else{
            this.totalPercentages = newTotalPercentages;
            log.info("Set totalPercentages to a new list");
        }
    }


}
