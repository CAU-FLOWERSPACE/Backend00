package cau.capstone.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Place {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long place_id;

  @Column(nullable = false)
  private String place;

  @Column(nullable = false)
  private String effect;
}
