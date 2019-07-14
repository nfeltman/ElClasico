package com.dugonggames.elclasico;

public class DistanceClassifier {
    DigitImage[] avgs;

    public DistanceClassifier(DigitImage[] images, int[] labels){
        avgs = new DigitImage[10];
        for (int i = 0; i < 10; i++) avgs[i] = new DigitImage(new float[784], 0);
        int[] counts = new int[10];

        for (int i = 0; i < 10000; i++){
            avgs[labels[i]] = avgs[labels[i]].add(images[i]);
            counts[labels[i]]++;
        }

        for (int i = 0; i < 10; i++) avgs[i] = avgs[i].divide(counts[i]);
    }

    public int eClassify(DigitImage image){
        double smallestDist = Double.POSITIVE_INFINITY;
        int smallestDistIndex= -1;
        for (int i = 0; i < 10; i++){
            if (image.eDistance(avgs[i]) < smallestDist){
                smallestDist = image.eDistance(avgs[i]);
                smallestDistIndex = i;
            }
        }
        return smallestDistIndex;
    }

    public int tClassify(DigitImage image){
        double smallestDist = Double.POSITIVE_INFINITY;
        int smallestDistIndex= -1;
        for (int i = 0; i < 10; i++){
            if (image.tDistance(avgs[i]) < smallestDist){
                smallestDist = image.tDistance(avgs[i]);
                smallestDistIndex = i;
            }
        }
        return smallestDistIndex;
    }
}
