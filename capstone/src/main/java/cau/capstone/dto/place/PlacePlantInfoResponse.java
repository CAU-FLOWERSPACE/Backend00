package cau.capstone.dto.place;

import cau.capstone.domain.Plant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlacePlantInfoResponse {
    private Long id;
    private String name;
    private String feature;
    private String image;

    public PlacePlantInfoResponse(Plant plant) {
        this.id = plant.getPlant_id();
        this.name = plant.getPlant_name();
        this.feature = plant.getPlant_feature();
        this.image = plant.getImage();
    }
}
