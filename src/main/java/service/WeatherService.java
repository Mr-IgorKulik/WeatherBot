package service;

import exceptions.IncorrectCityNameException;
import model.Weather;

import java.io.IOException;

import static service.ApiConstants.CITY_REGEX;

public interface WeatherService {

    Weather getByCityName (String city) throws IOException, InterruptedException;

    default void validateCityName (String city) {
        if (!city.matches(CITY_REGEX)) {
            throw new IncorrectCityNameException(String.format("Incorrect city name %s", city));
        }
    }

}
