package cau.capstone.service;

import cau.capstone.domain.Flower;
import cau.capstone.dto.flower.FlowerListResponse;
import cau.capstone.dto.flower.FlowerResponse;
import cau.capstone.repository.FlowerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FlowerService {

  private final FlowerRepository flowerRepository;

  // 꽃 추천 목록
  @Transactional(readOnly = true)
  public List<FlowerListResponse> 꽃추천목록(String color) {
    List<Flower> flowerList = flowerRepository.findByColor(color);
    List<FlowerListResponse> flowerListResponses = new ArrayList<>();
    for (Flower entity : flowerList) {
      flowerListResponses.add(new FlowerListResponse(entity));
    }

    return flowerListResponses;
  }

  // 꽃 상세보기
  @Transactional
  public FlowerResponse 꽃상세보기(Long flower_id) {
    Flower entity = flowerRepository.findById(flower_id).get();

    return new FlowerResponse(entity);
  }


}
