package cau.capstone.dto.flower;

import cau.capstone.domain.Flower;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FlowerListResponse {

  private Long id;

  private String name; // 꽃 이름

  private String image; // 이미지url

  public FlowerListResponse(Flower entity) {
    this.id = entity.getFlower_id();
    this.name = entity.getFlower_name();
    this.image = entity.getImage();
  }
}
