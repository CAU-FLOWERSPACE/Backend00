package cau.capstone.controller;

import cau.capstone.dto.flower.FlowerListResponse;
import cau.capstone.dto.flower.FlowerInfoResponse;
import cau.capstone.service.FlowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FlowerController {

  private final FlowerService flowerService;

  // 꽃추천목록조회 api
  @GetMapping("/api/flower/flowerList/{color}")
  public FlowerListResponse findFlowerList(@PathVariable String color) {
//    return flowerService.꽃추천목록(color, paging);
    return flowerService.recommendFlowerList(color);
  }

  // 꽃상세정보 api
  @GetMapping("/api/flower/{flower_id}")
  public FlowerInfoResponse findById(@PathVariable Long flower_id) {
    return flowerService.getFlowerInfo(flower_id);
  }


}
