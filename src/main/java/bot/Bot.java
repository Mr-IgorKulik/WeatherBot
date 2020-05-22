package bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import service.impl.WeatherServiceImpl;

import java.util.List;

public class Bot extends TelegramLongPollingBot {

    private ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    @Override
    public void onUpdateReceived(Update update) {
        var cityName = update.getMessage().getText();

        var service = new WeatherServiceImpl();

        var sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText(service.getByCityName(cityName).toString());


        KeyboardRow weatherKeyboardBtn = new KeyboardRow();
        weatherKeyboardBtn.add("Weather");

        KeyboardRow helloKeyboardBtn = new KeyboardRow();
        helloKeyboardBtn.add("Hello");

        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        replyKeyboardMarkup.setKeyboard(List.of(helloKeyboardBtn, weatherKeyboardBtn));
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /*private synchronized void sendMsg(String chatId, String msg) {

    }*/

    @Override
    public String getBotUsername() {
        return "IngvarsWeatherBot";
    }

    @Override
    public String getBotToken() {
        return "1086029574:AAF5lqQFfzQZFC1rQDGs7mwp-ZduIPlIJ-I";
    }
}
