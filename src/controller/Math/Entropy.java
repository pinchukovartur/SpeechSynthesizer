package controller.Math;


import static java.lang.Math.floor;

public class Entropy {

    public static double GetEntropy(byte[] source, int start, int finish,
                                    int binsCount, double minRaw, double maxRaw){
        double entropy = 0;

        double binSize =  Math.abs(maxRaw - minRaw) / binsCount;
        if ((int)binSize < Math.ulp(binSize)) {
            return 0;
        }
        double[] p = new double[binsCount];

        for (int i = start; i <= finish; i++) {
            double value = source[i];
            int index = (int) floor((value - minRaw) / binSize);

            if (index >= binsCount) {
                index = binsCount - 1;
            }

            p[index] += 1.;
        }

        // Normalize probabilities
        int size = finish - start + 1;
        for (int i = 0; i < binsCount; i++) {
            p[i] /= size;
        }

        // Calculate entropy
        for (int i = 0; i < binsCount; i++) {
            if (p[i] > Math.ulp(p[i])) {
                entropy += p[i] * log(p[i], 2);
            }
        }

        entropy = -entropy;

        return entropy;
    }

    static int log(double x, double base)
    {
        return (int) (Math.log(x) / Math.log(base));
    }
}
