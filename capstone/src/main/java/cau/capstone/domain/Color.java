package cau.capstone.domain;

import cau.capstone.dto.color.ColorClassification;
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

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ColorClassification color;

  private String effect;

//  @Column(nullable = false)
//  private int option_num;



}
