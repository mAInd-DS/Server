package com.mAInd.springboot.domain.counseling.dto.List;

import com.mAInd.springboot.domain.counseling.entity.MergedArray;
import com.mAInd.springboot.domain.counseling.entity.SentencePrediction;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.h2.command.dml.Merge;

@Getter
@NoArgsConstructor
public class MergedArrayDto {

    private String speakers;
    private String sentence;

    @Builder
    public MergedArrayDto(String speakers, String sentence){
        this.speakers = speakers;
        this.sentence = sentence;
    }

    public MergedArrayDto(MergedArray entity){
        this.speakers = entity.getSpeakers();
        this.sentence = entity.getSentence();
    }

    public MergedArray toEntity() {
        return new MergedArray(speakers, sentence);
    }
}
