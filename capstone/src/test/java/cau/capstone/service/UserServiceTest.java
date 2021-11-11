package cau.capstone.service;

import cau.capstone.domain.User;
import cau.capstone.dto.auth.JoinRequest;
import cau.capstone.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class UserServiceTest {

  @Autowired
  UserService userService;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @AfterEach
  public void aftereach() {
    try {
      userRepository.deleteAllInBatch();
    } catch (Exception e) {
    }
  }

  @Test
  void 회원가입() {

    String email = "test@test.com";
    String name = "test_name";
    String password = "test_password";


    //given
    JoinRequest joinRequest = JoinRequest.builder()
      .email(email)
      .name(name)
      .password(password)
      .build();

    //when
    userService.join(joinRequest);
    List<User> all = userRepository.findAll();

    //then
    assertThat(all.get(0).getEmail()).isEqualTo(email);
    assertThat((passwordEncoder.matches(password, all.get(0).getPassword()))).isTrue();
    assertThat(all.get(0).getName()).isEqualTo(name);
  }

//  void 회원가입실패() {
//
//  }
}

