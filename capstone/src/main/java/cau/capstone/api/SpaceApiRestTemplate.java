package cau.capstone.api;

import cau.capstone.api.dto.ApiRequest;
import cau.capstone.api.dto.ColorApiResponse;
import cau.capstone.api.dto.PlaceApiResponse;

import cau.capstone.dto.color.RGBColor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Configuration
public class SpaceApiRestTemplate {

    private final RestTemplate restTemplate;
    private final JSONParser jsonParser;

    public SpaceApiRestTemplate() {

        this.restTemplate = new RestTemplate();
        this.jsonParser = new JSONParser();
    }

    public PlaceApiResponse placeAPI(ApiRequest apiRequest) {
        ResponseEntity<String> response = getApiResponse( "https://apis.openapi.sk.com/urbanbase/v1/space/classifier", apiRequest);

        // Json parsing
        JSONObject body = null;
        try {
            body = (JSONObject) jsonParser.parse(response.getBody());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /* Objects.requireNonNull()
         * NPE 를 명시적으로 던지는 것이 JVM 이 발견해서 발생시키는 것 보다 성능상의 이점이 있다
         * requireNonNull 은 해당 참조가 null 일 경우 즉시 개발자에게 알려줌
         * */
        var data = (JSONObject) Objects.requireNonNull(body).get("data");
        var result = (JSONObject) ((JSONArray) ((JSONObject) ((JSONArray) data.get("results")).get(0)).get("results")).get(0);  // label, probability

        String spaceImageUuid = data.get("space_image_uuid").toString();
        String place = result.get("label").toString();

        if ((Objects.equals(place, "room")) || (Objects.equals(place, "dressingroom"))) {
            place = "bedroom";
        }
        else if (Objects.equals(place, "diningroom")) {
            place = "kitchen";
        }

        return new PlaceApiResponse(spaceImageUuid, place, (Double) result.get("probability"));
    }


    public ColorApiResponse colorAPI(ApiRequest apiRequest) {
        ResponseEntity<String> response = getApiResponse("https://apis.openapi.sk.com/urbanbase/v1/space/extractor", apiRequest);

        // Json parsing
        JSONObject body = null;
        try {
            body = (JSONObject) jsonParser.parse(response.getBody());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        var data = (JSONObject) Objects.requireNonNull(body).get("data");
        var results = (JSONArray)((JSONObject) ((JSONArray) data.get("results")).get(0)).get("results");  // rgb color array

        String spaceImageUuid = data.get("space_image_uuid").toString();
        List<RGBColor> rgbColors = null;
        try {
            rgbColors = objectMapper.readValue(results.toJSONString(), new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ColorApiResponse(spaceImageUuid, rgbColors);
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

        return restTemplate.exchange(uriComponents.toString(), HttpMethod.POST, entity, String.class);  // api response
    }

}
