package cau.capstone.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Recommend{ // 추천id | 식물id | 장소이름

  @Id
  private Long recommend_id;

  @ManyToOne
  @JoinColumn(name = "plant_id", referencedColumnName = "plant_id")
  private Plant plant;

  @ManyToOne
  @JoinColumn(name = "place", referencedColumnName = "place")
  private Place place;


}
