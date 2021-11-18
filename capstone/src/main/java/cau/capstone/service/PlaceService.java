package cau.capstone.service;

import cau.capstone.api.SpaceApiRestTemplate;
import cau.capstone.api.dto.ApiRequest;
import cau.capstone.api.dto.ColorApiResponse;
import cau.capstone.domain.Flower;
import cau.capstone.domain.Place;
import cau.capstone.domain.Plant;
import cau.capstone.domain.Recommend;
import cau.capstone.dto.color.ColorClassification;
import cau.capstone.dto.place.PlaceFlowerResponse;
import cau.capstone.dto.place.PlaceFlowerResponse.FlowerResponse;
import cau.capstone.dto.place.PlacePlantInfoResponse;
import cau.capstone.dto.place.PlacePlantResponse.PlantResponse;
import cau.capstone.dto.place.PlacePlantResponse;
import cau.capstone.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlantRepository plantRepository;
    private final FlowerRepository flowerRepository;
    private final PlaceRepository placeRepository;
    private final RecommendRepository recommendRepository;
    private final SpaceApiRestTemplate spaceApiRestTemplate;

    public PlacePlantResponse recommendPlacePlantList(ApiRequest apiRequest) {
        Place place = placeRepository.findByPlace(spaceApiRestTemplate.placeAPI(apiRequest).getPlace());
        List<Recommend> recommendList = recommendRepository.findRecommendsByPlace(place);
        List<PlantResponse> plantResponses = new ArrayList<>();

        for (Recommend entity : recommendList) {
            plantResponses.add(new PlantResponse(entity.getPlant()));
        }

        return new PlacePlantResponse(place.getPlace(), plantResponses);
    }

    public PlacePlantInfoResponse getPlacePlantInfo(Long id) {
        Plant plant = plantRepository.findById(id).get();

        return new PlacePlantInfoResponse(plant);
    }

    public PlaceFlowerResponse recommendPlaceFlowerList(ApiRequest apiRequest) {
        // ColorAlgoService 사용!
        ColorApiResponse colorApiResponse = spaceApiRestTemplate.colorAPI(apiRequest);
        ColorAlgorithm colorAlgorithm = new ColorAlgorithm(colorApiResponse.getRgbColors());

        // base 색상 기반 추천
        List<ColorClassification> recommandBaseColorList = colorAlgorithm.recommendBaseColor();
        // point 색상 기반 추천
        List<ColorClassification> recommandPointColorList = colorAlgorithm.recommendPointColor();

        List<Flower> flowerList = new ArrayList<>();
        List<FlowerResponse> flowerResponses = new ArrayList<>();

        // base color 에 맞는 꽃 리스트
        for (ColorClassification color: recommandBaseColorList) {
            if (colorAlgorithm.getBaseS() < 50) {
                flowerList.addAll(flowerRepository.findByColorAndSGreaterThanEqual(color.toString(), 50));
            } else {
                flowerList.addAll(flowerRepository.findByColorAndSLessThan(color.toString(),50));
            }
        }

        // point color 에 맞는 꽃 리스트
        for (ColorClassification color: recommandPointColorList) {
//            flowerList.addAll(flowerRepository.findFlowerByColor(color.toString()));
            if (colorAlgorithm.getBaseS() < 50) {
                flowerList.addAll(flowerRepository.findByColorAndSGreaterThanEqual(color.toString(), 50));
            } else {
                flowerList.addAll(flowerRepository.findByColorAndSLessThan(color.toString(),50));
            }
        }

        for(Flower entity : flowerList) {
            flowerResponses.add(new FlowerResponse(entity));
        }
        return new PlaceFlowerResponse(flowerResponses);
    }
}
