package com.mAInd.springboot.domain.counseling.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@Entity
public class TotalPercentages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String emotion;

    @Column(nullable = false)
    private Double percentage;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "counseling_id")
    private Counseling counseling;

    @Builder
    public TotalPercentages(String emotion, Double percentage) {
        this.emotion = emotion;
        this.percentage = percentage;
    }

    public void setCounseling(Counseling counseling) {
        this.counseling = counseling;
    }
}
