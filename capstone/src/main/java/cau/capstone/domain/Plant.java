package cau.capstone.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Plant {

  @Id
  private Long plant_id;

  @Column
  private String plant_name;

  @Column(nullable = false)
  private String plant_feature;

//  @Column(nullable = false)
//  private String place;

  @Column(length = 3000)
  private String image;
}
