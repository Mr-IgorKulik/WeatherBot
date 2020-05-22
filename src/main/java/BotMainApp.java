import config.BotConfig;
import exceptions.IncorrectCityNameException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.WeatherService;

import java.io.IOException;

public class BotMainApp {

    public static void main(String[] args) {
        var annotationConfigApplicationContext = new AnnotationConfigApplicationContext(BotConfig.class);
        var weatherService = annotationConfigApplicationContext.getBean(WeatherService.class);

        try {
            System.out.println(weatherService.getByCityName("Kharkiv"));
        } catch (IOException | InterruptedException ignored) {
        } catch (IncorrectCityNameException exc) {
            System.out.println(exc.getMessage());
        }

    }

}
