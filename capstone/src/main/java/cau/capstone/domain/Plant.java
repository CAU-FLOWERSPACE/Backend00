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

  @Column(length = 7000)
  private String plant_feature;

  @Column(length = 3000)
  private String image;
}
