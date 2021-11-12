package cau.capstone.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceApiResponse {

    private String spaceImageUuid;  // 범용 고유 식별자
    private String place;  // label
    private double probability;  // 분석 결과 확률
}
