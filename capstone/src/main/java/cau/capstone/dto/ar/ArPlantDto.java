package cau.capstone.dto.ar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArPlantDto {
    private List<ArPlantResponse> plants;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ArPlantResponse {
        private Long id;
        private String name;
        private String image;

        public ArPlantResponse(Long id, String name, String image) {
            this.id = id;
            this.name = name;
            this.image = image + "?timestamp=" + this.id;
        }
    }
}
