package in.ruzavin.telegramweatherbot.owpapi;

public class Weather {
    private final String json;

    public Weather(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return json;
    }
}
