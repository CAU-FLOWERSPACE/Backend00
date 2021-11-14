package cau.capstone.dto.place;

import cau.capstone.domain.Plant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlacePlantResponse {
    private String place;
    private List<PlantResponse> plants;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlantResponse {
        private Long plant_id;
        private String plant_name;
        private String image;
    }
}
