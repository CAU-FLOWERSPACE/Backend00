package cau.capstone.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3002") //해당 리액트 포트 번호.
@RestController
public class HelloController {

  @GetMapping("/api/test")
  public String test() {
    return "test success ~~~~~~~~~~!!";
  }
}
