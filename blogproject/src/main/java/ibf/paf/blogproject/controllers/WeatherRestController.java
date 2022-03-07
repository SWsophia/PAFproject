package ibf.paf.blogproject.controllers;

import ibf.paf.blogproject.model.Weather;
import ibf.paf.blogproject.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/weather", produces = MediaType.APPLICATION_JSON_VALUE)
public class WeatherRestController {

    @Autowired
    private WeatherService weatherSvc;

    @GetMapping(path="{city}")
    public ResponseEntity<String> getWeather(@PathVariable String city) {
        Weather weather = weatherSvc.getWeather(city);
        return ResponseEntity.ok(weather.toJson().toString());
    }
}
