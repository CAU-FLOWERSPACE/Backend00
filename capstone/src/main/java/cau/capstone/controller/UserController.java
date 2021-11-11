package cau.capstone.controller;

import cau.capstone.domain.User;
import cau.capstone.dto.auth.JoinRequest;
import cau.capstone.dto.auth.JoinResponse;
import cau.capstone.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

  private final UserService userService;

  // 회원가입

  @PostMapping("/api/join")
  public ResponseEntity<JoinResponse> join(@Valid @RequestBody JoinRequest joinRequest) {

    log.info("회원가입 요청 완료");

    Long saveId = userService.join(joinRequest);
    return new ResponseEntity<>(new JoinResponse(saveId), HttpStatus.CREATED);

  }

  // 이메일 중복체크
  @GetMapping("/api/user/check/email/{email}")
  public ResponseEntity<?> checkEmail(@PathVariable("email") String email) {
    log.info("Controller :: check duplicate email");

    if (userService.checkEmail(email)) {
      return ResponseEntity.status(HttpStatus.OK).body(false);
    }

    return ResponseEntity.status(HttpStatus.OK).body(true);
  }

}
