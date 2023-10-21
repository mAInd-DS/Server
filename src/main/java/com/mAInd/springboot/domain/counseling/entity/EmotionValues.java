package com.mAInd.springboot.domain.counseling.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@Entity
public class EmotionValues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double neutral;

    @Column(nullable = false)
    private Double happy;

    @Column(nullable = false)
    private Double surprise;

    @Column(nullable = false)
    private Double sadness;

    @Column(nullable = false)
    private Double anger;

    @Column(nullable = false)
    private Double disgust;

    @Column(nullable = false)
    private Double fear;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "counseling_id")
    private Counseling counseling;

    @Builder
    public EmotionValues(Double neutral, Double happy, Double surprise,
                         Double sadness, Double anger, Double disgust,
                         Double fear){
        this.neutral = neutral;
        this.happy = happy;
        this.surprise = surprise;
        this.sadness = sadness;
        this.anger = anger;
        this.disgust = disgust;
        this.fear = fear;
    }

    public void setCounseling(Counseling counseling) {
        this.counseling = counseling;
    }

}
