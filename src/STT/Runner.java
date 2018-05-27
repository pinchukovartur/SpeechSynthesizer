package STT;

import STT.controller.WAVController;
import STT.controller.WriteSoundThread;
import STT.view.UserInterface;

import javax.sound.sampled.*;
import java.io.File;

public class Runner {

    public static void main(String[] args) {
        // создаем окно интерфейса
        UserInterface window = new UserInterface(200, 150,
                "Run", "Stop", "Decode", "LogText");
        // создаем поток для аудио записи
        String saveTextPatch = "P:\\Projects\\SpeechSynthesizer\\test.wav";
        File outputFile = new File(saveTextPatch);
        AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                44100.0F, 16, 2, 4, 44100.0F, false);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
        TargetDataLine targetDataLine = null;
        try {
            targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
            targetDataLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            window.setLogText("unable to get a recording line");
            e.printStackTrace();
            System.exit(1);
        }

        AudioFileFormat.Type targetType = AudioFileFormat.Type.WAVE;
        WriteSoundThread soundThread = new WriteSoundThread(targetDataLine, targetType, outputFile);

        window.addActionOnRunButton(a -> {
            soundThread.start();
            window.setLogText("Recording started.");
        });

        window.addActionOnStopButton(a -> {
            soundThread.stopRecording();
            window.setLogText("Recording stopped.");
        });

        window.addActionOnDecodeButton(e -> window.setLogText(WAVController.ParseFile(saveTextPatch)));
    }

}
