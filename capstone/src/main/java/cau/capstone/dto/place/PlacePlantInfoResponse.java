package cau.capstone.dto.place;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlacePlantInfoResponse {
    private Long plant_id;
    private String plant_name;
    private String effect;
    private String plant_feature;
    private String place;
    private String image;
}
