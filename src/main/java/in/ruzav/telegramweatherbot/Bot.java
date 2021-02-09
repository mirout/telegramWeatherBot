package in.ruzav.telegramweatherbot;

import in.ruzav.telegramweatherbot.owpapi.OwpApi;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.URISyntaxException;

public class Bot extends TelegramLongPollingBot {
    private final String botName;
    private final String botToken;
    private final OwpApi owpApi;

    public Bot(String botName, String botToken, OwpApi owpApi) {
        this.botName = botName;
        this.botToken = botToken;
        this.owpApi = owpApi;
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
        try {
            sendMessage(msg.getChatId(), owpApi.getWeather(info[0], info[1]).toString());
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
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
