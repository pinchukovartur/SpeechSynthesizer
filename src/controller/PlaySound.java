package controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.List;

public class PlaySound {

    /*
       Метод проигрывает фонемы
    */
    public void playSound(List<String> phonemes) throws InterruptedException {
        for (String phoneme : phonemes) {

            if (phoneme.equals(" ")) {
                // задержка между пробелами
                Thread.sleep(300);
                continue;
            }

            try {
                String LOCAL = "WAVs//";

                File soundFile = new File(LOCAL + phoneme + ".wav");
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
