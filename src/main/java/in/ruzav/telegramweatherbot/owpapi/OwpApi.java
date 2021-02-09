package in.ruzav.telegramweatherbot.owpapi;

import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OwpApi {
    private final String apiKey;
    private final HttpClient client = HttpClient.newHttpClient();

    public OwpApi(final String owpToken) {
        apiKey = owpToken;
    }

    public Weather getWeather(String city, String country) throws IOException, InterruptedException, URISyntaxException {
        var uri = new URIBuilder()
                .setScheme("https")
                .setHost("api.openweathermap.org")
                .setPath("/data/2.5/weather")
                .addParameter("q", String.format("%s,%s", city, country))
                .addParameter("appid", apiKey)
                .addParameter("units", "metric");
        var request = HttpRequest.newBuilder(uri.build()).build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return new Weather(new JSONObject(response.body()));
    }
}
