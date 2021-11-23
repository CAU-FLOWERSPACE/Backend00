package cau.capstone.dto.place;

import cau.capstone.domain.Flower;
import cau.capstone.domain.Place;
import cau.capstone.domain.Plant;
import cau.capstone.domain.Recommend;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlacePlantInfoResponse {
    private Long id;
    private String name;
    private String feature;
    private String image;
//    private List<PlaceResponse> places;

    public PlacePlantInfoResponse(Plant plant) {  // , List<PlaceResponse> placeResponseList
        this.id = plant.getPlant_id();
        this.name = plant.getPlant_name();
        this.feature = plant.getPlant_feature();
        this.image = plant.getImage();
//        this.places = placeResponseList;
    }

//    @Getter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class PlaceResponse {
//        private String place;
//        private String effect;
//
//        public PlaceResponse(Place place) {
//            this.place = place.getPlace();
//            this.effect = place.getEffect();
//        }
//    }
}
