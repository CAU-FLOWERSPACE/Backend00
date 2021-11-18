package cau.capstone.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3002") //해당 리액트 포트 번호.
@RestController
@RequestMapping("/api")
public class HelloController {

  @GetMapping("/test")
  public String test() {
    return "test success ~~~~~~~~~~!!";
  }
}
