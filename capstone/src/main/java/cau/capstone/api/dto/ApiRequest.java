package cau.capstone.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor  // NoArgsConstructor 가 있어야 RequestBody 로 사용 가능
@AllArgsConstructor
public class ApiRequest {

    private String image_path;  // 사용자에게 받을 image dto
}
