package cau.capstone.api.dto;

import cau.capstone.dto.color.RGBColor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ColorApiResponse {

    private String spaceImageUuid;  // 범용 고유 식별자
    private List<RGBColor> rgbColors;  // color 5개
}
