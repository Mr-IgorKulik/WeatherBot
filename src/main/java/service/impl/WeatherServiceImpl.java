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

    public Weather getByCityName(String city) {
        validateCityName(city);
        var gson = new Gson();

        var httpClient = HttpClient.newBuilder()
                .build();

        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(GET_WEATHER_IN_CITY_URL + city + API_KEY_PARAM + apiKey))
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println("CATCH");
            e.printStackTrace();
        }

        WeatherResponse weathers = gson.fromJson(response.body(), WeatherResponse.class);

        return weathers.data[0];
    }

    class WeatherResponse {
        Weather[] data;
    }
}
