package cau.capstone.service;

import cau.capstone.domain.User;
import cau.capstone.dto.auth.JoinRequest;
import cau.capstone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

  private final PasswordEncoder passwordEncoder;

  private final UserRepository userRepository;

  /***
   * 회원가입 서비스
   */

  @Transactional
  public Long join(JoinRequest joinRequest) {

    joinRequest.setPassword(passwordEncoder.encode(joinRequest.getPassword()));
    User userEntity = joinRequest.toEntity();

    return userRepository.save(userEntity).getId();

  }

  @Transactional
  public boolean checkEmail(String email) {
    log.info("check duplicate email service");
    return userRepository.existsByEmail(email);
  }
}
