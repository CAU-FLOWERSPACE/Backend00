package cau.capstone.service;

import cau.capstone.domain.Flower;
import cau.capstone.dto.flower.FlowerListResponse;
import cau.capstone.dto.flower.FlowerListResponse.FlowerResponse;
import cau.capstone.dto.flower.FlowerInfoResponse;
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
  public FlowerListResponse recommendFlowerList(String color) {
    List<Flower> flowerList = flowerRepository.findFlowersByColor(color);
    List<FlowerResponse> flowerResponseList = new ArrayList<>();
    for (Flower entity : flowerList) {
      flowerResponseList.add(new FlowerResponse(entity));
    }


    return new FlowerListResponse(color, flowerResponseList);
  }

  // 꽃 상세보기
  @Transactional
  public FlowerInfoResponse getFlowerInfo(Long flower_id) {
    Flower entity = flowerRepository.findById(flower_id).get();

    return new FlowerInfoResponse(entity);
  }


}
