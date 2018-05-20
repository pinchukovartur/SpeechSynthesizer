import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.List;

public class PlaySound {

    private final String LOCAL = "WAVs//";


    public void playSound(List<String> fonems) throws InterruptedException {
        for (int i = 0; i < fonems.size(); i++) {
            if(fonems.get(i).equals(" ")){
                Thread.sleep(300);
                continue;
            }
            try {
                File soundFile = new File(LOCAL+fonems.get(i)+".wav");
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                Thread.sleep(clip.getMicrosecondLength()/1000);
                clip.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
