package controller;

import model.WAVData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RecognizeController {

    public void RecognizeController(String filename)
    {
        Path path = Paths.get(filename);
        try {
            byte[] data = Files.readAllBytes(path);
            WAVData recognizeFile = new WAVData(data);
            recognizeFile.GetFrames();

            byte[] dataF = Files.readAllBytes(path);
            WAVData first = new WAVData(dataF);
            byte[] dataS = Files.readAllBytes(path);
            WAVData second = new WAVData(dataS);
            byte[] dataT = Files.readAllBytes(path);
            WAVData third = new WAVData(dataT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
