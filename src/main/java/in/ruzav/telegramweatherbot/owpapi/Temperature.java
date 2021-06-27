package in.ruzav.telegramweatherbot.owpapi;

public class Temperature {
    private final double temperature;
    private final double feelsLike;
    private final double tempMin;
    private final double tempMax;

    public Temperature(double temperature, double feelsLike, double tempMin, double tempMax) {
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    @Override
    public String toString() {
        return String.format("{%n" +
                "Temperature: %s C;%n" +
                "Feels like: %s C;%n" +
                "Min: %s C;%n" +
                "Max: %s C;%n}", temperature, feelsLike, tempMin, getTempMax());
    }
}
