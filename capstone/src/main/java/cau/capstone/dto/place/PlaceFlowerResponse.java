package cau.capstone.dto.place;

import cau.capstone.domain.Flower;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceFlowerResponse {
    private List<FlowerResponse> flowers;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FlowerResponse {
        private Long id;
        private String name;
        private String image;

        public FlowerResponse(Flower flower) {
            this.id = flower.getFlower_id();
            this.name = flower.getFlower_name();
            this.image = flower.getImage();
        }
    }
}
