package com.dugonggames.elclasico;

public class Tree {
    int splitIndex;
    float splitThreshold;
    int leftBest;
    int rightBest;

    public Tree(DigitImage[] images, int[] labels){
        float bestPurity = 0f;
        int bestIndex = -1;
        float bestThreshold = -1f;
        for (int i = 0; i < 784; i++){
            for (float j = 0; j < 1; j += 1.0/255){
                ClassCounts left = new ClassCounts(10);
                ClassCounts right = new ClassCounts(10);
                for (int k = 0; k < images.length; k++){
                    if (images[k].getDigit()[i] < j) left.increment(labels[k]);
                    else right.increment(labels[k]);
                }
                if (left.gimiPurity() + right.gimiPurity() > bestPurity){
                    bestPurity = left.gimiPurity() + right.gimiPurity();
                    bestIndex = i;
                    bestThreshold = j;
                    leftBest = left.highestValue();
                    rightBest = right.highestValue();
                }
            }
            System.out.println(i);
        }
        splitIndex = bestIndex;
        splitThreshold = bestThreshold;
    }

    public int classify(DigitImage image){
        if (image.getDigit()[splitIndex] < splitThreshold) return leftBest;
        else return rightBest;
    }
}
