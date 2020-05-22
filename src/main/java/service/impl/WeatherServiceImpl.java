package service.impl;

import com.google.gson.Gson;
import model.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import service.WeatherService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static service.ApiConstants.API_KEY_PARAM;
import static service.ApiConstants.GET_WEATHER_IN_CITY_URL;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${api.key}")
    private String apiKey;

    public String getByCityName(String city) {
        validateCityName(city);
        var gson = new Gson();

        var httpClient = HttpClient.newBuilder()
                .build();

        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(GET_WEATHER_IN_CITY_URL + city + API_KEY_PARAM + "c37e2350a6d34a358b04e1c8de6b13fd"))
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        WeatherResponse weathers = gson.fromJson(response.body(), WeatherResponse.class);

        var responseStr = "Invalid city name!";
        try {
            if (weathers.data.length == 1) {
                responseStr = weathers.data[0].toString();
            }
        } catch (NullPointerException ignored) {
        }
        return responseStr;
    }

    static class WeatherResponse {
        Weather[] data;
    }
}
