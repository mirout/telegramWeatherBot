package in.ruzav.telegramweatherbot.owpapi;

import org.json.JSONObject;

public class Weather {

    private final Temperature temperature;
    private final double windSpeed;
    private final double windDeg;
    private final String status;
    private final Place place;

    public Weather(JSONObject json, Place place) {
        temperature = new Temperature(
                json.getJSONObject("main").getDouble("temp"),
                json.getJSONObject("main").getDouble("feels_like"),
                json.getJSONObject("main").getDouble("temp_min"),
                json.getJSONObject("main").getDouble("temp_max")
                );
        this.windSpeed = json.getJSONObject("wind").getDouble("speed");
        this.windDeg = json.getJSONObject("wind").getDouble("deg");
        this.status = json.getJSONArray("weather").getJSONObject(0).getString("main");
        this.place = place;
    }

    @Override
    public String toString() {
        return String.format("Weather at %s;%n" +
                "Temperature: %s;%n" +
                "Condition: %s;%n" +
                "Wind speed: %f;%n" +
                "Wind degree: %f;", place.getCity(), temperature, status, windSpeed, windDeg);
    }
}
