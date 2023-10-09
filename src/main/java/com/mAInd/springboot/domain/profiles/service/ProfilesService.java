package com.mAInd.springboot.domain.profiles.service;

import com.mAInd.springboot.domain.profiles.entity.Profiles;
import com.mAInd.springboot.domain.profiles.repository.ProfilesRepository;
import com.mAInd.springboot.domain.profiles.dto.ProfilesListResponseDto;
import com.mAInd.springboot.domain.profiles.dto.ProfilesResponseDto;
import com.mAInd.springboot.domain.profiles.dto.ProfilesSaveRequestDto;
import com.mAInd.springboot.domain.profiles.dto.ProfilesUpdateRequestDto;
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
        return profilesRepository.save(requestDto.toEntity()).getProfile_id();
    }

    @Transactional
    public Long update(Long profile_id, ProfilesUpdateRequestDto requestDto){
        Profiles profiles = profilesRepository.findById(profile_id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. profile_id="+profile_id));
        profiles.update(requestDto.getTitle(), requestDto.getCareer(), requestDto.getEducation(),
                requestDto.getContent());

        return profile_id;
    }

    @Transactional
    public void delete (Long profile_id) {
        Profiles profiles = profilesRepository.findById(profile_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. profile_id=" + profile_id));

        profilesRepository.delete(profiles);
    }

    @Transactional(readOnly = true)
    public ProfilesResponseDto findById(Long profile_id){
        Profiles entity = profilesRepository.findById(profile_id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 게시글이 없습니다. profile_id=" + profile_id));
        return new ProfilesResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<ProfilesListResponseDto> findAllDesc() {
        return profilesRepository.findAllDesc().stream()
                .map(ProfilesListResponseDto::new)
                .collect(Collectors.toList());
    }

}
