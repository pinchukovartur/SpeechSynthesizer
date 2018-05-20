import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class WAVData {
    private String chunkID;
    private int chunkSize;
    private String format;
    private int subChunk1ID;
    private int subChunk1Size;
    private String audioFormat;
    private String numChannels;
    private int sampleRate;
    private int byteRate;
    private String blockAlign;
    private String bitsPerSample;
    private String subChunk2ID;
    private int subChunk2Size;

    private byte[] data;
    private List<byte[]> frems;


    public WAVData(byte[] wavData) {
        chunkID = WAVData.CreateString(wavData, new int[]{0, 1, 2, 3});
        chunkSize = WAVData.CreateInt(wavData, new int[]{4, 5, 6, 7}, true);
        format = WAVData.CreateString(wavData, new int[]{8, 9, 10, 11});
        subChunk1ID = WAVData.CreateInt(wavData, new int[]{12, 13, 14, 15}, true);
        subChunk1Size = WAVData.CreateInt(wavData, new int[]{16, 17, 18, 19}, true);
        audioFormat = WAVData.CreateString(wavData, new int[]{20, 21}); // не инт
        numChannels = WAVData.CreateString(wavData, new int[]{22, 23}); // не инт
        sampleRate = WAVData.CreateInt(wavData, new int[]{24, 25, 26, 27}, true);
        byteRate = WAVData.CreateInt(wavData, new int[]{28, 29, 30, 31}, true);
        blockAlign = WAVData.CreateString(wavData, new int[]{32, 33}); // не инт
        bitsPerSample = WAVData.CreateString(wavData, new int[]{34, 35}); // не инт
        subChunk2ID = WAVData.CreateString(wavData, new int[]{36, 37, 38, 39});
        subChunk2Size = WAVData.CreateInt(wavData, new int[]{40, 41, 42, 43}, true);
        data = WAVData.CreateWavData(wavData, 44);


    }

    private static String CreateString(byte[] data, int[] numbers) {
        byte[] result = new byte[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            result[i] = data[numbers[i]];
        }
        return new String(result);
    }

    private static int CreateInt(byte[] data, int[] numbers, boolean useLittleEdian) {
        byte[] result = new byte[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            result[i] = data[numbers[i]];
        }
        ByteBuffer bb = ByteBuffer.wrap(result);
        if (useLittleEdian)
            bb.order(ByteOrder.LITTLE_ENDIAN);
        return bb.getInt();
    }

    public static byte[] CreateWavData(byte[] data, int startIndex) {
        byte[] result = new byte[data.length - startIndex];

        for (int i = 0; i < result.length; i++) {
            result[i] = data[i + startIndex];
        }
        return result;
    }

    public static List<byte[]> CreateFrems(byte[] data, int freamSize, int fremNah) {
        List<byte[]> result = new ArrayList<>();

        byte[] frem = new byte[freamSize];

        int startIndex = 0;
        int endIndex = freamSize;
        for (int i = 0; i < data.length; i++)
        {
            byte[] item = WAVData.GetBytesInInterval(data, startIndex, endIndex);
            startIndex = 0;
            endIndex = 0;

        }


        return result;
    }


    public static byte[] GetBytesInInterval(byte[] data, int startIndex, int endIndex) {
        byte[] result = new byte[endIndex - startIndex];
        for (int i = 0; i < data.length; i++) {
            result[i] = data[startIndex + i];
            if (i + startIndex >= endIndex)
                break;
        }
        return result;
    }
}
