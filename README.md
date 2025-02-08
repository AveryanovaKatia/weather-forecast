# REST API Weather Forecast

API для получения прогноза погоды (температура воздуха) в запрашиваемом городе.
Сервис разворачивается в контейнерах Docker с использованием Nginx для проксирования запросов к приложению.

Данные о погоде запрашиваются с публичного сервиса [OpenWeatherMap](https://openweathermap.org).

### Основные возможности:
- Получение текущей температуры, минимальной и максимальной температуры для указанного города.
- Доступ к API через Swagger UI.

### Стек технологий:
#### Java 21, Maven, Spring Boot, REST API, Lombok, Slf4j, Docker, Nginx, Swagger.


### Инструкция по сборке:

1.Установить необходимые инструменты:

    - [Java 21](https://openjdk.org/projects/jdk/21/)
    - [Maven](https://maven.apache.org/install.html)
    - [Docker](https://docs.docker.com/get-docker/)

2.Перейти в корневую директорию проекта.

3.Выполнить команду для сборки проекта:
#### mvn clean install


### Инструкция по запуску:

Собрать и запустить контейнеры:
#### docker compose up --build -d

Проверить, что контейнеры запущены:
#### docker ps


### Обратиться к API через Swagger:

Ввести в адресную строку браузера:
####  http://localhost:8080/swagger-ui.html

В поле "cityName" указать название города на английском языке, пример ссылки с указанным городом:
#### http://localhost/api/getWeather/Samara 

####  Пример ответа:
json
     {
          "temp": 10,
          "temp_min": 6,
          "temp_max": 12
     }

### Инструкция по остановке/удалению контейнеров:

#### docker compose down

Удалить контейнеры, сети и образы:
#### docker compose down --rmi all