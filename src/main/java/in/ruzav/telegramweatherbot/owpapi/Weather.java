package in.ruzav.telegramweatherbot.owpapi;

import org.json.JSONObject;

public class Weather {
    private final double temperature;
    private final double windSpeed;
    private final double windDeg;
    private final String status;
    private final Place place;

    public Weather(JSONObject json, Place place) {
        this.temperature = json.getJSONObject("main").getDouble("temp");
        this.windSpeed = json.getJSONObject("wind").getDouble("speed");
        this.windDeg = json.getJSONObject("wind").getDouble("deg");
        this.status = json.getJSONArray("weather").getJSONObject(0).getString("main");
        this.place = place;
    }

    @Override
    public String toString() {
        return String.format("Weather at %s;%n" +
                "Temperature: %g C;%n" +
                "Main: %s; %nWind speed: %f;%n" +
                "Wind degree: %f;", place.getCity(), temperature, status, windSpeed, windDeg);
    }
}
