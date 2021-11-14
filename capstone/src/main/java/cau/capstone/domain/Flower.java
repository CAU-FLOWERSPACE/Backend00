package cau.capstone.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Flower{

  @Id
  private Long flower_id;

  @Column(nullable = false)
  private String flower_name;

  @Column(nullable = false)
  private String mean;

  @Column(nullable = false)
  private String flower_feature;

  @Column(nullable = false)
  private String color;

  @Column(length = 3000)
  private String image;

  @Column
  private int r;

  @Column
  private int g;

  @Column
  private int b;

  @Column
  private int h;

  @Column
  private int s;

  @Column
  private int v;
}
