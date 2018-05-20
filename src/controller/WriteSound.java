package controller;


import javax.sound.sampled.*;
import javax.sound.sampled.AudioFileFormat.Type;
import java.io.File;
import java.io.IOException;

public class WriteSound extends Thread {
    private TargetDataLine m_line;
    private AudioFileFormat.Type m_targetType;
    private AudioInputStream m_audioInputStream;
    private File m_outputFile;

    public WriteSound(TargetDataLine m_line, Type m_targetType, File m_outputFile) {
        this.m_line = m_line;
        this.m_targetType = m_targetType;
        this.m_audioInputStream = new AudioInputStream(m_line);
        this.m_outputFile = m_outputFile;
    }

    public static void main(String args[]) {
        File outputFile = new File("D:\\SpeechSynthesizer\\test.wav");

        AudioFormat audioFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                44100.0F, 16, 2, 4, 44100.0F, false);

        DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
        TargetDataLine targetDataLine = null;
        try {
            targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
            targetDataLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            System.out.println("unable to get a recording line");
            e.printStackTrace();
            System.exit(1);
        }

        AudioFileFormat.Type targetType = AudioFileFormat.Type.WAVE;

        WriteSound j = new WriteSound(targetDataLine, targetType, outputFile);

        j.start();

        System.out.println("Recording started.");

        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        j.stopRecording();

        System.out.println("Recording stopped.");
    }

    public void start() {
        m_line.start();
        super.start();
    }

    public void stopRecording() {
        m_line.stop();
        m_line.close();
    }

    public void run() {
        try {
            AudioSystem.write(
                    m_audioInputStream,
                    m_targetType,
                    m_outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}