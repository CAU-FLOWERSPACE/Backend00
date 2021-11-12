package cau.capstone.dto.flower;

import cau.capstone.domain.Flower;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class FlowerListResponse {

  private Long flower_id;

  private String flower_name; // 꽃 이름

  private String image; // 이미지url

  public FlowerListResponse(Flower entity) {
    this.flower_id= entity.getFlower_id();
    this.flower_name = entity.getFlower_name();
    this.image = entity.getImage();
  }
}
