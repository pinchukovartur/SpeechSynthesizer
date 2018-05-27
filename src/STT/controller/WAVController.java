package STT.controller;


import STT.model.WAVFile;
import STT.model.WAVFrame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class WAVController {

    public static String ParseFile(String filePath)
    {
        Path path = Paths.get(filePath);
        try {
            byte[] data = Files.readAllBytes(path);
            WAVFile soundData = new WAVFile(data);

            List<WAVFrame> frames = CreateFrames(soundData.getData(), 10, 5);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "None";
    }


    private static List<WAVFrame> CreateFrames(byte[] data, int frameSize, int frameOverlapping) {

        List<WAVFrame> result = new ArrayList<>();

        int start = 0;
        int end = frameSize;
        int nextIndex = 0;
        for (int i = 0; i < data.length; i++) {
            if (nextIndex == i) {
                WAVFrame newFrame = new WAVFrame(result.size() + 1, start, end, GetBytesInInterval(data, start, end));
                start = end - frameOverlapping;
                end = end + frameSize - frameOverlapping;
                nextIndex = nextIndex + frameSize - frameOverlapping;
                result.add(newFrame);
            }
        }


        return result;
    }

    private static byte[] GetBytesInInterval(byte[] data, int startIndex, int endIndex) {

        if (data.length < endIndex) {
            endIndex = data.length;
        }

        byte[] result = new byte[endIndex - startIndex];
        for (int i = 0; i < data.length; i++) {
            result[i] = data[startIndex + i];
            if (i + startIndex >= endIndex - 1)
                break;
        }
        return result;
    }
}
