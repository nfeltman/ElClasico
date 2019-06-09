package com.dugonggames.elclasico;

public class DigitImage {
    float[] digit = new float[784];

    public DigitImage(byte[] bytearr, int offset){
        for (int i = 0; i < 784; i++){
            float f = bytearr[i+offset]/256f;
            digit[i] = f<0 ? f+1 : f;
        }
    }

    public DigitImage(float[] digit){
        this.digit = digit;
    }

    public void print(){
        for (int i = 0; i < 783; i++){
            if (i % 28 == 0) System.out.println();
            if (digit[i] > 0.5) System.out.print("*");
            else if (digit[i] > 0.25) System.out.print(".");
            else System.out.print(" ");
        }
    }

    public DigitImage add(DigitImage other){
        float[] result = new float[784];
        for (int i = 0; i < 784; i++) result[i] = digit[i] + other.digit[i];
        return new DigitImage(result);
    }

    public DigitImage subtract(DigitImage other){
        float[] result = new float[784];
        for (int i = 0; i < 784; i++) result[i] = digit[i] - other.digit[i];
        return new DigitImage(result);
    }

    // Euclidian distance
    public double eDistance(DigitImage other){
        double sum = 0;
        for (int i = 0; i < 784; i++) sum += (digit[i] - other.digit[i]) * (digit[i] - other.digit[i]);
        return Math.sqrt(sum);
    }

    // Taxicab distance
    public double tDistance(DigitImage other){
        double sum = 0;
        for (int i = 0; i < 784; i++) sum += Math.abs(digit[i] - other.digit[i]);
        return sum;
    }

    public DigitImage divide(int denom){
        float[] result = new float[784];
        for (int i = 0; i < 784; i++) result[i] = digit[i]/denom;
        return new DigitImage(result);
    }
}
