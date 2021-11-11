package cau.capstone.dto.auth;

import cau.capstone.domain.Role;
import cau.capstone.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class JoinRequest {

  private String email;
  private String name;
  private String password;

  @Builder
  public JoinRequest(String email, String name, String password) {
    this.email = email;
    this.name = name;
    this.password = password;
  }

  public User toEntity() {

    return User.builder()
      .email(email)
      .name(name)
      .password(password)
      .role(Role.ROLE_USER)
      .build();

  }



}
