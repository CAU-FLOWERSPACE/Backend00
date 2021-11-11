package cau.capstone.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Flower {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long flower_id;

  @Column(nullable = false)
  private String flower_name;

  @Column(nullable = false)
  private String mean;

  @Column(nullable = false)
  private String flower_feature;

  @Column(nullable = false)
  private String color;


  private String image;

  private int r;
  private int g;
  private int b;
  private int h;
  private int s;
  private int v;


}
