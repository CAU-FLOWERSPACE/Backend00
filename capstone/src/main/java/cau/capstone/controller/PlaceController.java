package cau.capstone.controller;

import cau.capstone.api.dto.ApiRequest;
import cau.capstone.dto.place.PlaceFlowerResponse;
import cau.capstone.dto.place.PlacePlantInfoResponse;
import cau.capstone.dto.place.PlacePlantResponse;
import cau.capstone.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PlaceController {

    private final PlaceService placeService;

    // 공간 분석 api 호출 restTemplate 에게 ApiRequest 넘겨주기
    @PostMapping("/place/plant")  // image upload
    @ResponseBody
    public PlacePlantResponse postPlacePlants(@RequestBody ApiRequest apiRequest) {
        return placeService.recommendPlacePlantList(apiRequest);
    }

    @GetMapping("/place/plant/{plant_id}")
    public PlacePlantInfoResponse getPlacePlant(@PathVariable Long plant_id) {
        return placeService.getPlacePlantInfo(plant_id);
    }

    @PostMapping("/place/flower")
    @ResponseBody
    public PlaceFlowerResponse postPlaceFlowers(@RequestBody ApiRequest apiRequest) {
        return placeService.recommendPlaceFlowerList(apiRequest);
    }
}
