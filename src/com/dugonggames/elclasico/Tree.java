package com.dugonggames.elclasico;

import java.util.Arrays;
import java.util.Comparator;

public class Tree {
    int splitIndex;
    float splitThreshold;
    int leftBest;
    int rightBest;

    public Tree(DigitImage[] images){
        buildTree(images, 2, 0, images.length);
    }

    public static Node<Integer> buildTree(DigitImage[] images, int maxDepth, int low, int high){
        ClassCounts countAll = new ClassCounts(10);
        for (int j = low; j < high; j++){ countAll.increment(images[j].getLabel()); }

        if (maxDepth <= 1) return new Leaf<Integer>(countAll.highestValue());
        float bestPurity = 0f;
        int bestIndex = -1;
        float bestThreshold = -1f;


        for (int i = 0; i < 784; i++){
            final int f = i;
            Arrays.sort(images, low, high, Comparator.comparing(d->d.getDigit()[f]));
            ClassCounts left = new ClassCounts(10);
            ClassCounts right = new ClassCounts(10);
            for (int j = low; j < high; j++){ right.increment(images[j].getLabel()); }
            for (int j = low; j < high; j++){
                if (j > low){
                    right.decrement(images[j-1].getLabel());
                    left.increment(images[j-1].getLabel());
                }
                if (left.gimiPurity() + right.gimiPurity() > bestPurity){
                    bestPurity = left.gimiPurity() + right.gimiPurity();
                    bestIndex = i;
                    bestThreshold = images[j].getDigit()[i];
                    //leftBest = left.highestValue();
                    //rightBest = right.highestValue();
                }
            }
            System.out.println(i);
        }
        int pi = partition(images, bestIndex, bestThreshold, low, high);
        return new Branch<Integer>(buildTree(images, maxDepth-1, low, pi), buildTree(images, maxDepth-1, pi, high), bestIndex, bestThreshold);
    }

    private static int partition(DigitImage[] images, int pixel, float threshold, int low, int high) {
        int i = (low);
        for (int j=low; j<high; j++) {
            if(images[j].getDigit()[pixel] < threshold) {
                i++;
                DigitImage temp = images[i];
                images[i] = images[j];
                images[j] = temp;
            }
        }
        DigitImage temp = images[i+1];
        images[i] = images[high];
        images[high] = temp;
        return i;
    }

    public int classify(DigitImage image){
        if (image.getDigit()[splitIndex] < splitThreshold) return leftBest;
        else return rightBest;
    }
}
