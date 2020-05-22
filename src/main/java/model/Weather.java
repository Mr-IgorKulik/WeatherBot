package model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Weather {

    private String city_name;
    private String country_code;
    private String sunrise;
    private String sunset;
    private double wind_spd;
    private double temp;

    @Override
    public String toString() {
        return "Your city: " + city_name + '\n' +
                "Your country: " + country_code + '\n' +
                "Current temperature: " + temp  + '\n' +
                "Sunrise Time: " + sunrise + '\n' +
                "Sunset Time: " + sunset + '\n' +
                "Wind Speed: " + wind_spd;
    }
}
