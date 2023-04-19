package com.mAInd.springboot.web.dto;
import com.mAInd.springboot.domain.profiles.Profiles;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfilesSaveRequestDto {
    private String title;
    private String career;
    private String education;
    private String content;
    private String counselor_id;

    @Builder
    public ProfilesSaveRequestDto(String title, String career, String education, String content, String counselor_id){
        this.title = title;
        this.career = career;
        this.education = education;
        this.content = content;
        this.counselor_id = counselor_id;
    }

    public Profiles toEntity(){
        return Profiles.builder()
                .title(title)
                .career(career)
                .education(education)
                .content(content)
                .counselor_id(counselor_id)
                .build();
    }
}
