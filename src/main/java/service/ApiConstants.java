package service;

public final class ApiConstants {

    public static final String CITY_REGEX = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$";
    public static final String GET_WEATHER_IN_CITY_URL = "https://api.weatherbit.io/v2.0/current?city=";
    public static final String API_KEY_PARAM = "&key=";
    public static final int OK_STATUS_CODE = 200;
    public static final int NOT_FOUND_STATUS_CODE = 404;

    private ApiConstants() {
    }
}
