package bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        String text = update.getMessage().getText();
        String firstName = update.getMessage().getFrom().getFirstName();

        System.out.println("msg: " + text + " | from: " + firstName);
        sendMsg(update.getMessage().getChatId().toString(), text);
    }

    private synchronized void sendMsg(String chatId, String msg) {
        var sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(msg);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "IngvarsWeatherBot";
    }

    @Override
    public String getBotToken() {
        return "1086029574:AAF5lqQFfzQZFC1rQDGs7mwp-ZduIPlIJ-I";
    }
}
