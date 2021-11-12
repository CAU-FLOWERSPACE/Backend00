package cau.capstone.dto.flower;

import cau.capstone.domain.Flower;
import lombok.Getter;

@Getter
public class FlowerResponse {

  private String flower_name; // 꽃 이름

  private String mean; // 꽃말

  private String flower_feature; // 꽃특징

  private String color; // 꽃색

  private String image; // 이미지url


  public FlowerResponse(Flower entity) {
    this.flower_name = entity.getFlower_name();
    this.mean = entity.getMean();
    this.flower_feature = entity.getFlower_feature();
    this.color = entity.getColor();
    this.image = entity.getImage();
  }
}
