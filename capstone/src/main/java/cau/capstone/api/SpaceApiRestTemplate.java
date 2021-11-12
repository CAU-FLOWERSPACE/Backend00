package cau.capstone.api;

import cau.capstone.api.dto.ColorApiRequest;
import cau.capstone.api.dto.ColorApiResponse;
import cau.capstone.api.dto.PlaceApiRequest;
import cau.capstone.api.dto.PlaceApiResponse;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class SpaceApiRestTemplate {

    private final RestTemplate restTemplate;

    public PlaceApiResponse placeAPI(PlaceApiRequest placeApiRequest) {
        String spaceApiUrl = "https://apis.openapi.sk.com/urbanbase/v1/space/classifier";  // POST

        // UriComponents
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(spaceApiUrl)
                .build(false);  // encoded false

        // Header
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        headers.add("appkey", "");

        // body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("image_path", placeApiRequest.getImage_path());

        // HttpEntity 는 header 와 body 를 합쳐준다
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.exchange(uriComponents.toString(), HttpMethod.POST, entity, String.class);

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

        System.out.println(result.get("label").toString());
        System.out.println(result.get("probability").toString());
        PlaceApiResponse placeApiResponse = new PlaceApiResponse(spaceImageUuid, result.get("label").toString(), (Double) result.get("probability"));

        return placeApiResponse;
    }


    public ColorApiResponse colorAPI(ColorApiRequest colorApiRequest) {
        String colorApiUrl = "https://apis.openapi.sk.com/urbanbase/v1/space/extractor";  // POST

        // UriComponents
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(colorApiUrl)
                .build(false);  // encoded false

        // Header
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        headers.add("appkey", "");

        // body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("image_path", colorApiRequest.getImage_path());

        // HttpEntity 는 header 와 body 를 합쳐준다
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.exchange(uriComponents.toString(), HttpMethod.POST, entity, String.class);


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

        results.forEach(System.out::println);
        ColorApiResponse colorApiResponse = new ColorApiResponse(spaceImageUuid, results);

        return colorApiResponse;
    }
}
