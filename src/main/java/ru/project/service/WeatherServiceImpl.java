package ru.project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.project.model.GeoResponse;
import ru.project.model.OpenWeatherMapResponse;
import ru.project.model.Weather;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    @Value("${openweathermap.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public Weather getWeather(final String cityName) {

        log.info("Запрос на получение географических данных города " + cityName);

        final String geoUrl = "http://api.openweathermap.org/geo/1.0/direct?q=" + cityName + "&limit=1&appid=" + apiKey;

        final GeoResponse[] geoResponse = restTemplate.getForObject(geoUrl, GeoResponse[].class);

        double lat;

        double lon;

        if (Objects.nonNull(geoResponse) && geoResponse.length > 0) {

            log.info("Координаты успешно получены.");

            lat = geoResponse[0].getLat();
            lon = geoResponse[0].getLon();
        } else {
            throw new RuntimeException("Не удалось получить данные о координатах города " + cityName);
        }

        log.info("Запрос на получение прогноза погоды с openweathermap");

        final String weatherUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat
                + "&lon=" + lon + "&units=metric&appid=" + apiKey;
        final OpenWeatherMapResponse response = restTemplate.getForObject(weatherUrl, OpenWeatherMapResponse.class);

        if (Objects.nonNull(response)) {
            log.info("Данные о погоде успешно получены. Сегодня, в городе {} температура = {} грасудам Цельсия",
                     cityName, (int) response.getMain().getTemp());

            return new Weather(
                    (int) response.getMain().getTemp(),
                    (int) response.getMain().getTempMin(),
                    (int) response.getMain().getTempMax()
            );
        } else {
            throw new RuntimeException("Не удалось получить данные о погоде в городе " + cityName);
        }
    }
}
