package cau.capstone.service;

import cau.capstone.api.SpaceApiRestTemplate;
import cau.capstone.api.dto.ApiRequest;
import cau.capstone.api.dto.ColorApiResponse;
import cau.capstone.dto.color.ColorClassification;
import cau.capstone.dto.place.PlaceFlowerResponse;
import cau.capstone.dto.place.PlaceFlowerResponse.FlowerResponse;
import cau.capstone.dto.place.PlacePlantInfoResponse;
import cau.capstone.dto.place.PlacePlantResponse;
import cau.capstone.dto.place.PlacePlantResponse.PlantResponse;
import cau.capstone.repository.FlowerRepository;
import cau.capstone.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlantRepository plantRepository;
    private final FlowerRepository flowerRepository;
    private final SpaceApiRestTemplate spaceApiRestTemplate;

    public PlacePlantResponse recommendPlacePlants(ApiRequest apiRequest) {
        String place = spaceApiRestTemplate.placeAPI(apiRequest).getPlace();
        List<PlantResponse> plantResponseList = plantRepository.findPlantByPlace(place);  // place 활용 repo 로 넘겨주기

        return new PlacePlantResponse(place, plantResponseList);
    }

    public PlacePlantInfoResponse getPlacePlantInfo(Long id) {

        return plantRepository.findPlantById(id);
    }

    public PlaceFlowerResponse recommandPlaceFlowers(ApiRequest apiRequest) {
        // ColorAlgoService 사용!
        ColorApiResponse colorApiResponse = spaceApiRestTemplate.colorAPI(apiRequest);
        ColorAlgorithm colorAlgorithm = new ColorAlgorithm(colorApiResponse.getRgbColors());

        // base 색상 기반 추천
        List<ColorClassification> recommandBaseColorList = colorAlgorithm.recommendBaseColor();

        List<ColorClassification> recommandPointColorList = colorAlgorithm.recommendPointColor();

        List<FlowerResponse> flowerResponses = new ArrayList<>();

        // base color 에 맞는 꽃 리스트
        for (ColorClassification color: recommandBaseColorList) {
            if (colorAlgorithm.getBaseS() < 50) {
                flowerResponses.addAll(flowerRepository.findByColorAndSGreaterThanEqual(color, 50));
            } else {
                flowerResponses.addAll(flowerRepository.findByColorAndSLessThan(color,50));
            }
        }

        // base color 에 맞는 꽃 리스트
        for (ColorClassification color: recommandPointColorList) {
            flowerResponses.addAll(flowerRepository.findByColor(color));
        }

        return new PlaceFlowerResponse(flowerResponses);
    }
}
