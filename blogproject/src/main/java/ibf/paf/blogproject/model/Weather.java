package ibf.paf.blogproject.model;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Weather {
    String city;
    String main;
    String description;
    String icon;
    Float temperature;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "city: %s, main: %s, description: %s".formatted(city, main, description);
    }

    public static Weather toWeather(String s) {
        try (InputStream is = new ByteArrayInputStream(s.getBytes())) {
            JsonReader reader = Json.createReader(is);
            return toWeather(reader.readObject());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Weather toWeather(JsonObject o) {
        Weather w = new Weather();
        w.setCity(o.getString("name"));
        JsonArray arr = o.getJsonArray("weather");
        if (!arr.isEmpty()) {
            JsonObject wo = arr.getJsonObject(0);
            w.setMain(wo.getString("main"));
            w.setDescription(wo.getString("description"));
            w.setIcon(wo.getString("icon"));
        }
        w.setTemperature((float)o.getJsonObject("main").getJsonNumber("temp").intValue());
        return (w);
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("city", city)
                .add("main", main)
                .add("description", description)
                .add("icon", icon)
                .add("temperature", temperature)
                .build();

    }

}
