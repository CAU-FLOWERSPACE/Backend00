package cau.capstone.dto.place;

import cau.capstone.domain.Plant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlacePlantInfoResponse {
    private Long plant_id;
    private String plant_name;
    private String plant_feature;
    private String image;

    public PlacePlantInfoResponse(Plant plant) {
        this.plant_id= plant.getPlant_id();
        this.plant_name = plant.getPlant_name();
        this.plant_feature = plant.getPlant_feature();
        this.image = plant.getImage();
    }
}
