package com.mAInd.springboot.domain.counseling.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Results {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long result_id;

    @Column(nullable = false)
    private Long survey_id;

    @Column(nullable = false)
    private Long countNum;


//    @ElementCollection
    @OneToMany(mappedBy = "results", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SentencePrediction> sentencePredictions;


    @Builder
    public Results(Long survey_id, Long countNum, List<SentencePrediction> sentencePredictions){
        this.survey_id = survey_id;
        this.countNum = countNum;
        this.sentencePredictions = sentencePredictions;
    }




}

