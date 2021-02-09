package in.ruzavin.telegramweatherbot;

import in.ruzavin.telegramweatherbot.owpapi.OwpApi;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> env = System.getenv();
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot(env.get("BOT_NAME"), env.get("BOT_TOKEN"), new OwpApi(env.get("OWP_TOKEN"))));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
