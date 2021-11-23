package cau.capstone.dto.place;

import cau.capstone.domain.Plant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlacePlantResponse {
    private String place;
    private String effect;
    private List<PlantResponse> plants;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlantResponse {
        private Long id;
        private String name;
        private String image;

        public PlantResponse(Plant plant) {
            this.id = plant.getPlant_id();
            this.name = plant.getPlant_name();
            this.image = plant.getImage();
        }
    }
}
