package STT.Math.model;

public class WAVFrame {

    private int number;
    private int startIndex;
    private int endIndex;
    private byte[] data;
    private int entropy;
    private double[] fourierSeries;
    private double [] fourierWithHamming;

    public WAVFrame(int number, int startIndex, int endIndex, byte[] data)
    {
        this.number = number;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.data = data;
        this.entropy = GetEntropy(data);
        this.fourierSeries = GetFourierSeries(data);
        this.fourierWithHamming = GetHammingFunction(fourierSeries);
    }

    private int GetEntropy(byte[] data){
        int result = 0;
        for (int i = 0; i < data.length; i++) {

            result += data[i] * Math.log(data[i]);
        }
        return result;
    }

    private double [] GetHammingFunction(double [] data){
        double [] result = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            double hammingNumber = 0.54 - 0.46 * Math.cos(2 * 3.14 * i / (data.length - 1));
            result[i] = data[i] * hammingNumber;
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
