package in.ruzav.telegramweatherbot.owpapi;

public class Place {
    private final String city;
    private final String country;

    public Place(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public Place(String str) {

        String[] split = str.split(" ");
        StringBuilder cityStringBuilder = new StringBuilder();

        for (int i = 0; i < split.length - 1; i++) {
            cityStringBuilder.append(split[i]).append(" ");
        }

        cityStringBuilder.setLength(cityStringBuilder.length() - 1);

        this.city = cityStringBuilder.toString();
        this.country = split[split.length - 1];
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
