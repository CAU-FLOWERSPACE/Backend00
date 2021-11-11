package cau.capstone.dto.auth;

import lombok.Getter;

@Getter
public class JoinResponse {

  private Long id;

  public JoinResponse(Long id) {
    this.id = id;
  }
}
