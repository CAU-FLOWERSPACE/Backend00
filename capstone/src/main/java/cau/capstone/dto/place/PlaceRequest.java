package cau.capstone.dto.place;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRequest {

    //    private User user;  // 비회원인 경우? 나중에는 비회원 유저도 만들어야 할 듯. 우선 유저 정보는 저장하지 않는다.
    private String image;  // user 가 업로드 한 이미지
}
