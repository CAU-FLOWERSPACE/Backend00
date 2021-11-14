package cau.capstone.dto.place;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceFlowerResponse {
    private List<FlowerResponse> flowers;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FlowerResponse {
        private Long flower_id;
        private String flower_name;
        private String image;
    }
}
