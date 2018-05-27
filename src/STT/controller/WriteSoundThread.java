package STT.controller;


import javax.sound.sampled.*;
import javax.sound.sampled.AudioFileFormat.Type;
import java.io.File;
import java.io.IOException;

public class WriteSoundThread extends Thread {
    private TargetDataLine m_line;
    private AudioFileFormat.Type m_targetType;
    private AudioInputStream m_audioInputStream;
    private File m_outputFile;


    /** Реализация потока записи аудифайла
     * @param line
     * @param targetType
     * @param outputFile
     */
    public WriteSoundThread(TargetDataLine line, Type targetType, File outputFile) {
        this.m_line = line;
        this.m_targetType = targetType;
        this.m_audioInputStream = new AudioInputStream(line);
        this.m_outputFile = outputFile;
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