package cau.capstone.service;

import cau.capstone.domain.Plant;
import cau.capstone.dto.place.PlacePlantResponse;
import cau.capstone.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlantService {

    private final PlantRepository plantRepository;

    public PlacePlantResponse recommendPlants() {


        return new PlacePlantResponse();
    }

//    public Plant plantInfo() {
//
//    }
}
