package in.ruzav.telegramweatherbot;

import in.ruzav.telegramweatherbot.owpapi.Place;
import in.ruzav.telegramweatherbot.owpapi.Weather;
import in.ruzav.telegramweatherbot.owpapi.exception.NotFoundException;
import in.ruzav.telegramweatherbot.owpapi.OwpApi;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {
    private final String botName;
    private final String botToken;
    private final OwpApi owpApi;
    private final Map<Long, Place> cityByUser;

    public Bot(String botName, String botToken, OwpApi owpApi) {
        this.botName = botName;
        this.botToken = botToken;
        this.owpApi = owpApi;
        this.cityByUser = new HashMap<>();
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage();
        String[] info = msg.getText().split(" ");
        if (msg.isCommand()) {
            command(msg);
            return;
        }
        try {
            sendMessage(msg.getChatId(), owpApi.getWeather(info[0], info[1]).toString());
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            sendMessage(msg.getChatId(), e.getMessage());
        }
    }

    private void command(Message msg) {
        String text = msg.getText();
        if (text.startsWith("/start")) {
            sendMessage(msg.getChatId(), "Information about bot. This bot using Open Weather Map api." +
                    "\nUse /get City Country: for get weather");
        } else if (text.startsWith("/get")) {
            String[] spl = text.split(" ");
            if (spl.length == 1 && !cityByUser.containsKey(msg.getChatId())) {
                sendMessage(msg.getChatId(), "Please, enter city and country");
                return;
            } else if (spl.length == 1) {
                try {
                    Weather weather = owpApi.getWeather(cityByUser.get(msg.getChatId()));
                    sendMessage(msg.getChatId(), weather.toString());
                } catch (IOException | InterruptedException | URISyntaxException e) {
                    e.printStackTrace();
                } catch (NotFoundException e) {
                    sendMessage(msg.getChatId(), e.getMessage());
                }
                return;
            }

            Place place = new Place(text.substring(4));
            try {
                Weather weather = owpApi.getWeather(place);
                sendMessage(msg.getChatId(), weather.toString());
            } catch (IOException | InterruptedException | URISyntaxException e) {
                e.printStackTrace();
            } catch (NotFoundException e) {
                sendMessage(msg.getChatId(), e.getMessage());
            }
        } else if (text.startsWith("/set")) {
            String[] spl = text.split(" ");

            if (spl.length == 1) {
                sendMessage(msg.getChatId(), "Please, enter city and country");
            }

            Place place = new Place(text.substring(4));
            cityByUser.put(msg.getChatId(), place);
            sendMessage(msg.getChatId(), String.format("You set: %s %s", place.getCity(), place.getCountry()));
        }
    }

    private void sendMessage(long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
