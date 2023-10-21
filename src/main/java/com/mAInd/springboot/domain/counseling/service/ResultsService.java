//package com.mAInd.springboot.domain.counseling.service;
//
//import com.mAInd.springboot.domain.counseling.dto.ResultsSaveRequestDto;
//import com.mAInd.springboot.domain.counseling.repository.ResultsRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@RequiredArgsConstructor
//@Service
//public class ResultsService {
//
//    private final ResultsRepository resultsRepository;
//
//    @Transactional
//    public Long save(ResultsSaveRequestDto requestDto){
//        return resultsRepository.save(requestDto.toEntity()).getResult_id();
//    }
//
////    @Transactional
////    public ResultsResponseDto findById(Long result_id){
////        Results entity = resultsRepository.findById(result_id)
////                .orElseThrow(()-> new
////                        IllegalArgumentException("해당 상담이 없습니다. result_id=" + result_id));
////        return new ResultsResponseDto(entity);
////    }
//}
