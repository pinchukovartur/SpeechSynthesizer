package TTS;

import TTS.controller.PhonemeController;
import TTS.controller.PlaySound;
import TTS.view.UserInterface;

import java.util.List;

public class Runner {

    public static void main(String[] args) {
        // создаем окно интерфейса
        UserInterface window = new UserInterface(500, 150,
                "Run", 30, "");

        // вешаем экшен на кнопку
        window.addActionOnButton(e -> {
            String inputText = window.getInputFieldText();
            // парсим фонемы
            List<String> phonemes = PhonemeController.getPhonemes(inputText);
            // выводим фонемы
            window.setLogText(phonemes.toString());
            // проигрываем фонемы
            PlaySound.playPhonemes(phonemes, "WAVs//", 300);
        });
    }
}
