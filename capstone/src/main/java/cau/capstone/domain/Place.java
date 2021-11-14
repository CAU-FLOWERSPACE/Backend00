package cau.capstone.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Place {

  @Id
  private String place;

  @Column(length = 1000)
  private String effect;
}
