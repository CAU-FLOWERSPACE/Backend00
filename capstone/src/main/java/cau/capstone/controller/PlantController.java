package cau.capstone.controller;

import cau.capstone.dto.place.PlaceFlowerResponse;
import cau.capstone.dto.place.PlacePlantResponse;
import cau.capstone.dto.place.PlaceRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PlantController {

    // 공간 분석 api 호출 restTemplate 에게 placeRequest 넘겨주기
    @PostMapping("/place/plant")  // image upload
    public PlacePlantResponse plantPost(@RequestBody PlaceRequest placeRequest) {

        return new PlacePlantResponse();
    }

    @PostMapping("/place/flower")
    public PlaceFlowerResponse flowerPost(@RequestBody PlaceRequest placeRequest) {

        return new PlaceFlowerResponse();
    }
}
