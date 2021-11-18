package cau.capstone.controller;

import cau.capstone.dto.flower.FlowerListResponse;
import cau.capstone.dto.flower.FlowerResponse;
import cau.capstone.service.FlowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FlowerController {

  private final FlowerService flowerService;

  // 꽃추천목록조회 api
  @GetMapping("/flower/flowerList/{color}")
  public List<FlowerListResponse> findFlowerList(@PathVariable String color) {
//    return flowerService.꽃추천목록(color, paging);
    return flowerService.recommendFlowerList(color);
  }

  // 꽃상세정보 api
  @GetMapping("/flower/{flower_id}")
  public FlowerResponse findById(@PathVariable Long flower_id) {
    return flowerService.getFlowerInfo(flower_id);
  }


}
