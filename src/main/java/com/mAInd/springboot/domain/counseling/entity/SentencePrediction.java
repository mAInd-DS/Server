package com.mAInd.springboot.domain.counseling.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@Entity
public class SentencePrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sentence;

    @Column(nullable = false)
    private String emotion;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "result_id")
    private Results results;

    @Builder
    public SentencePrediction(String sentence, String emotion, Results results) {
        this.sentence = sentence;
        this.emotion = emotion;
        this.results =results;
    }
}
