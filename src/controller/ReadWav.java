package controller;

import model.WAVData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ReadWav {

    public void ReadWavFile(String filename)
    {
        Path path = Paths.get(filename);
        try {
            byte[] data = Files.readAllBytes(path);
            WAVData dataWAV = new WAVData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
