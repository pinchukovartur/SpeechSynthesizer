package STT.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class WAVFile {

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

    public WAVFile(byte[] wavData) {
        chunkID = WAVFile.CreateString(wavData, new int[]{0, 1, 2, 3});
        chunkSize = WAVFile.CreateInt(wavData, new int[]{4, 5, 6, 7});
        format = WAVFile.CreateString(wavData, new int[]{8, 9, 10, 11});
        subChunk1ID = WAVFile.CreateInt(wavData, new int[]{12, 13, 14, 15});
        subChunk1Size = WAVFile.CreateInt(wavData, new int[]{16, 17, 18, 19});
        audioFormat = WAVFile.CreateString(wavData, new int[]{20, 21}); // не инт
        numChannels = WAVFile.CreateString(wavData, new int[]{22, 23}); // не инт
        sampleRate = WAVFile.CreateInt(wavData, new int[]{24, 25, 26, 27});
        byteRate = WAVFile.CreateInt(wavData, new int[]{28, 29, 30, 31});
        blockAlign = WAVFile.CreateString(wavData, new int[]{32, 33}); // не инт
        bitsPerSample = WAVFile.CreateString(wavData, new int[]{34, 35}); // не инт
        subChunk2ID = WAVFile.CreateString(wavData, new int[]{36, 37, 38, 39});
        subChunk2Size = WAVFile.CreateInt(wavData, new int[]{40, 41, 42, 43});
        data = CreateWavData(wavData);

        //frems = CreateFrames(data, 10, 5);
    }

    private static String CreateString(byte[] data, int[] numbers) {
        byte[] result = new byte[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            result[i] = data[numbers[i]];
        }
        return new String(result);
    }

    private static int CreateInt(byte[] data, int[] numbers) {
        byte[] result = new byte[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            result[i] = data[numbers[i]];
        }
        ByteBuffer bb = ByteBuffer.wrap(result);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        return bb.getInt();
    }

    private byte[] CreateWavData(byte[] data) {
        byte[] result = new byte[data.length - 44];
        System.arraycopy(data, 44, result, 0, result.length);
        return result;
    }

    public String getChunkID() {
        return chunkID;
    }

    public int getChunkSize() {
        return chunkSize;
    }

    public String getFormat() {
        return format;
    }

    public int getSubChunk1ID() {
        return subChunk1ID;
    }

    public int getSubChunk1Size() {
        return subChunk1Size;
    }

    public String getAudioFormat() {
        return audioFormat;
    }

    public String getNumChannels() {
        return numChannels;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public int getByteRate() {
        return byteRate;
    }

    public String getBlockAlign() {
        return blockAlign;
    }

    public String getBitsPerSample() {
        return bitsPerSample;
    }

    public String getSubChunk2ID() {
        return subChunk2ID;
    }

    public int getSubChunk2Size() {
        return subChunk2Size;
    }

    public byte[] getData() {
        return data;
    }
}
