package model;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

public class WAVFrame {

    private int number;
    private int startIndex;
    private int endIndex;
    private byte[] data;
    private int entropy;
    private double[] fourierSeries;

    public WAVFrame(int number, int startIndex, int endIndex, byte[] data)
    {
        this.number = number;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.data = data;
        this.entropy = GetEntropy(data);
        this.fourierSeries = GetFourierSeries(data);
    }

    private int GetEntropy(byte[] data){
        int result = 0;
        for (int i = 0; i < data.length; i++) {

            result += data[i] * Math.log(data[i]);
        }
        return result;
    }

    private double[] GetFourierSeries(byte[] data){
        double[] result = new double[data.length];

        for (int i = 0; i < data.length; i++) {
            result[i] = GetOneFourier(i, data);
        }
        return result;
    }

    private double GetOneFourier(int vectorNumber,  byte[] data){
        double result = 0.0;
        for (int i = 0; i < data.length; i++) {
            result += data[i] * Math.pow(2.72, -2 * 3.14 * i * vectorNumber / data.length); // тут еще нит
        }
        return result;
    }

    public int getNumber() {
        return number;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public byte[] getData() {
        return data;
    }
}
