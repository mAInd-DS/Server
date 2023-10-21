package com.mAInd.springboot.domain.counseling.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@Entity
public class MergedArray {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String speakers;

    @Column(nullable = false)
    private String sentence;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "counseling_id")
    private Counseling counseling;

    @Builder
    public MergedArray(String speakers, String sentence) {
        this.speakers = speakers;
        this.sentence = sentence;
    }

    public void setCounseling(Counseling counseling) {
        this.counseling = counseling;
    }


}
