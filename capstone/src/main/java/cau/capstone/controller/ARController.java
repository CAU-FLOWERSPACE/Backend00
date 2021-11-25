package cau.capstone.controller;

import cau.capstone.dto.ar.ArPlantDto;
import cau.capstone.dto.ar.ArPlantDto.ArPlantResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@Controller
public class ARController {

    // AR 누르는 api 하나 더 생성
    @RequestMapping(value = "/api/ar", method = RequestMethod.POST, produces="application/json; charset=utf-8;")  // POST
    public String placeAR(@RequestBody ArPlantDto arPlantDto, Model model) {  //
//        List<ArPlantResponse> arPlantResponses = new ArrayList<>();
//        arPlantResponses.add(new ArPlantResponse(63L, "제라니움", "https://dlagpwjd-flower.s3.ap-northeast-2.amazonaws.com/plant1/제라니움.jpg"));
//        arPlantResponses.add(new ArPlantResponse(63L, "제라니움", "https://dlagpwjd-flower.s3.ap-northeast-2.amazonaws.com/plant1/제라니움.jpg"));
//        arPlantResponses.add(new ArPlantResponse(63L, "제라니움", "https://dlagpwjd-flower.s3.ap-northeast-2.amazonaws.com/plant1/제라니움.jpg"));
//        arPlantResponses.add(new ArPlantResponse(63L, "제라니움", "https://dlagpwjd-flower.s3.ap-northeast-2.amazonaws.com/plant1/제라니움.jpg"));
//        arPlantResponses.add(new ArPlantResponse(63L, "제라니움", "https://dlagpwjd-flower.s3.ap-northeast-2.amazonaws.com/plant1/제라니움.jpg"));
//        ArPlantDto arPlantDto1 = new ArPlantDto(arPlantResponses);

        for (ArPlantResponse entity : arPlantDto.getPlants()) {
            System.out.println(entity.getImage());
        }
//        model.addAttribute("plantList", arPlantDto1.getPlants());
        model.addAttribute("plantList", arPlantDto.getPlants());

        return "webAR";
    }

    @RequestMapping(value = "/api/ar", method = RequestMethod.GET, produces="application/json; charset=utf-8;")  // POST
    public String placeAR(Model model) {  //
        List<ArPlantResponse> arPlantResponses = new ArrayList<>();
        arPlantResponses.add(new ArPlantResponse(63L, "제라니움", "https://dlagpwjd-flower.s3.ap-northeast-2.amazonaws.com/plant1/제라니움.jpg"));
        arPlantResponses.add(new ArPlantResponse(63L, "제라니움", "https://dlagpwjd-flower.s3.ap-northeast-2.amazonaws.com/plant1/제라니움.jpg"));
        arPlantResponses.add(new ArPlantResponse(63L, "제라니움", "https://dlagpwjd-flower.s3.ap-northeast-2.amazonaws.com/plant1/제라니움.jpg"));
        arPlantResponses.add(new ArPlantResponse(63L, "제라니움", "https://dlagpwjd-flower.s3.ap-northeast-2.amazonaws.com/plant1/제라니움.jpg"));
        arPlantResponses.add(new ArPlantResponse(63L, "제라니움", "https://dlagpwjd-flower.s3.ap-northeast-2.amazonaws.com/plant1/제라니움.jpg"));
        ArPlantDto arPlantDto1 = new ArPlantDto(arPlantResponses);

        model.addAttribute("plantList", arPlantDto1.getPlants());

        return "webAR";
    }
}
