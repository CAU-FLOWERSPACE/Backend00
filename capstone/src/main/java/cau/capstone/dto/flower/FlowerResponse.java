package cau.capstone.dto.flower;

import cau.capstone.domain.Flower;
import lombok.Getter;

@Getter
public class FlowerResponse {

  private String name; // 꽃 이름

  private String mean; // 꽃말

  private String feature; // 꽃특징

  private String color; // 꽃색

  private String image; // 이미지url


  public FlowerResponse(Flower entity) {
    this.name = entity.getFlower_name();
    this.mean = entity.getMean();
    this.feature = entity.getFlower_feature();
    this.color = entity.getColor();
    this.image = entity.getImage();
  }
}
