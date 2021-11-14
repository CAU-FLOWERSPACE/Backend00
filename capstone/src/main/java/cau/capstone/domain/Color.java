package cau.capstone.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Color {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long color_id;

  @Column(nullable = false)
  private ColorClassification color;

  @Column(nullable = false)
  private String effect;

//  @Column(nullable = false)
//  private int option_num;



}
