package cau.capstone.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Plant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long plant_id;

  @Column(nullable = false)
  private String plant_name;

  @Column(nullable = false)
  private String effect;

  @Column(nullable = false)
  private String plant_feature;

  @Column(nullable = false)
  private String place;

  // 이미지
}
