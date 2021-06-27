package in.ruzav.telegramweatherbot.owpapi;

public class Place {
    private final String city;
    private final String country;

    public Place(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public Place(String str) {

        String[] split = str.split(",");

        if (split.length != 2) {
            throw new IllegalArgumentException();
        }

        this.city = split[0];
        this.country = split[1];
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
