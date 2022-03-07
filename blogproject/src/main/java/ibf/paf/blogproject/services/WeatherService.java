package ibf.paf.blogproject.services;

import ibf.paf.blogproject.model.Weather;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Service
public class WeatherService {

    public static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";
    public static final String ENV_OPENWEATHERMAP_KEY = "OPENWEATHERMAP_KEY";

    private final String key;

    public WeatherService() {
//        key = System.getenv("ENV_OPENWEATHERMAP_KEY");
        key = "ad54ad6007baf81821292e2f18b27d90";
    }

    public Weather getWeather(String city) {
        final String url = UriComponentsBuilder
                .fromUriString(WEATHER_URL)
                .queryParam("q", city.replaceAll(" ", "\\+"))
                .queryParam("units", "metric")
                .queryParam("appid", key)
                .toUriString();

        final RequestEntity<Void> req = RequestEntity.get(url).build();

        final RestTemplate template = new RestTemplate();

        final ResponseEntity<String> resp = template.exchange(req, String.class);

        return Weather.toWeather(resp.getBody());
    }
}
