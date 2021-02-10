package in.ruzav.telegramweatherbot.owpapi.exception;

public class NotFoundException extends OwpApiException{
    public NotFoundException(String city, String country) {
        super(String.format("Not found city: %s in %s", city, country));
    }
}
