import controller.PlaySound;
import controller.ReadWav;
import view.MainWindow;

public class Runner {

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        PlaySound playSound = new PlaySound();
        ReadWav redController = new ReadWav();
        redController.ReadWavFile("P:\\Projects\\SpeechSynthesizer\\WAVs\\м.wav");
    }
}
