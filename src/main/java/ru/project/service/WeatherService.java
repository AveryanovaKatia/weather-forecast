package ru.project.service;

import ru.project.model.Weather;

public interface WeatherService {

    Weather getWeather(final String cityName);
}
