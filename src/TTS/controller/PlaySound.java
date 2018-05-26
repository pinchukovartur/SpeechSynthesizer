package TTS.controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.List;

public class PlaySound {

    /**
     * Метод проигрывает фонемы по их названию (название wav  файла должно соответствовать фонеме)
     *
     * @param phonemes  лист фонем
     * @param localPath путь к wav файлам с фонемами
     * @param spaceTime время между словами
     */
    public static void playPhonemes(List<String> phonemes, String localPath, int spaceTime) {
        for (String phoneme : phonemes) {
            try {
                // задержка между словами
                if (phoneme.equals(" ")) {
                    Thread.sleep(spaceTime);
                    continue;
                }
                File soundFile = new File(localPath + phoneme + ".wav");
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                Thread.sleep(clip.getMicrosecondLength() / 1000);
                clip.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
