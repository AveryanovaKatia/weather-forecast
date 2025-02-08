package ru.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.model.Weather;
import ru.project.service.WeatherService;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "Weather Controller", description = "Контроллер для получения прогноза погоды в заданном городе")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("api/getWeather/{cityName}")
    @Operation(summary = "Запрос на получение прогноза погоды",
               description = "Возвращает прогноз погоды в заданном городе")
    public Weather getWeather(@PathVariable final String cityName) {
        return weatherService.getWeather(cityName);
    }
}