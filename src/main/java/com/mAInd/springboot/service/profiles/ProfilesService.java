package com.mAInd.springboot.service.profiles;

import com.mAInd.springboot.domain.profiles.Profiles;
import com.mAInd.springboot.domain.profiles.ProfilesRepository;
import com.mAInd.springboot.web.dto.ProfilesListResponseDto;
import com.mAInd.springboot.web.dto.ProfilesResponseDto;
import com.mAInd.springboot.web.dto.ProfilesSaveRequestDto;
import com.mAInd.springboot.web.dto.ProfilesUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProfilesService {
    private final ProfilesRepository profilesRepository;

    @Transactional
    public Long save(ProfilesSaveRequestDto requestDto){
        return profilesRepository.save(requestDto.toEntity()).getCounselor_profile_id();
    }

    @Transactional
    public Long update(Long id, ProfilesUpdateRequestDto requestDto){
        Profiles profiles = profilesRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        profiles.update(requestDto.getTitle(), requestDto.getCareer(), requestDto.getEducation(),
                requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Profiles profiles = profilesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        profilesRepository.delete(profiles);
    }

    public ProfilesResponseDto findById(Long id){
        Profiles entity = profilesRepository.findById(id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new ProfilesResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<ProfilesListResponseDto> findAllDesc() {
        return profilesRepository.findAllDesc().stream()
                .map(ProfilesListResponseDto::new)
                .collect(Collectors.toList());
    }

}
