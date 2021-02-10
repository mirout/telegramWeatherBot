package in.ruzav.telegramweatherbot.owpapi;

import org.json.JSONObject;

public class Weather {
    private final double temperature;
    private final double windSpeed;
    private final double windDeg;
    private final String status;

    public Weather(JSONObject json) {
        temperature = json.getJSONObject("main").getDouble("temp");
        windSpeed = json.getJSONObject("wind").getDouble("speed");
        windDeg = json.getJSONObject("wind").getDouble("deg");
        status = json.getJSONArray("weather").getJSONObject(0).getString("main");
    }

    @Override
    public String toString() {
        return String.format("Temperature: %g C; %nMain: %s; %nWind speed: %f; %nWind degree: %f;", temperature, status, windSpeed, windDeg);
    }
}
