package cau.capstone.api;

import cau.capstone.api.dto.ApiRequest;
import cau.capstone.api.dto.ColorApiResponse;
import cau.capstone.api.dto.PlaceApiResponse;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class SpaceApiRestTemplate {

    private final RestTemplate restTemplate;

    public PlaceApiResponse placeAPI(ApiRequest apiRequest) {
        ResponseEntity<String> response = getApiResponse( "https://apis.openapi.sk.com/urbanbase/v1/space/classifier", apiRequest);

        JSONParser jsonParser = new JSONParser();
        JSONObject body = null;
        try {
            body = (JSONObject) jsonParser.parse(response.getBody());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        var data = (JSONObject) body.get("data");
        var result = (JSONObject) ((JSONArray) ((JSONObject) ((JSONArray) data.get("results")).get(0)).get("results")).get(0);  // label, probability
        String spaceImageUuid = data.get("space_image_uuid").toString();

        PlaceApiResponse placeApiResponse = new PlaceApiResponse(spaceImageUuid, result.get("label").toString(), (Double) result.get("probability"));

        return placeApiResponse;
    }


    public ColorApiResponse colorAPI(ApiRequest apiRequest) {
        ResponseEntity<String> response = getApiResponse( "https://apis.openapi.sk.com/urbanbase/v1/space/extractor", apiRequest);

        JSONParser jsonParser = new JSONParser();
        JSONObject body = null;
        try {
            body = (JSONObject) jsonParser.parse(response.getBody());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        var data = (JSONObject) body.get("data");
        var results = (JSONArray) ((JSONObject) ((JSONArray) data.get("results")).get(0)).get("results");  // RGB list
        String spaceImageUuid = data.get("space_image_uuid").toString();

//        ColorApiResponse colorApiResponse = new ColorApiResponse(spaceImageUuid, results);

        return new ColorApiResponse(spaceImageUuid, results);
    }

    private ResponseEntity<String> getApiResponse(String spaceApiUrl, ApiRequest apiRequest) {
        // UriComponents
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(spaceApiUrl)
                .build(false);  // encoded false

        // Header
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        headers.add("appkey", "l7xx815fff2f0c064971be003e8a5f9b428c");

        // HttpEntity 는 header 와 body 를 합쳐준다
        HttpEntity<ApiRequest> entity = new HttpEntity<>(apiRequest, headers);

//        ResponseEntity<String> response = restTemplate.exchange(uriComponents.toString(), HttpMethod.POST, entity, String.class);

        return restTemplate.exchange(uriComponents.toString(), HttpMethod.POST, entity, String.class);
    }
}
