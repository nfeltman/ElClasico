package com.dugonggames.elclasico;

import java.util.Arrays;
import java.util.Comparator;

public class Tree {
    Node<Integer> tree;

    public Tree(DigitImage[] images){
        tree = buildTree(images, 3, 0, images.length);
    }

    public static Node<Integer> buildTree(DigitImage[] images, int maxDepth, int low, int high){
        ClassCounts countAll = new ClassCounts(10);
        for (int j = low; j < high; j++){ countAll.increment(images[j].getLabel()); }

        if (maxDepth <= 1) return new Leaf<Integer>(countAll.highestValue());
        float bestPurity = 0f;
        int bestIndex = -1;
        float bestThreshold = -1f;


        ClassCounts all = new ClassCounts(10);
        for (int j = low; j < high; j++){ all.increment(images[j].getLabel()); }

        for (int i = 0; i < images[low].numFeatures(); i++){
            final int f = i;
            Arrays.sort(images, low, high, Comparator.comparing(d->d.getDigit()[f]));
            ClassCounts left = new ClassCounts(10);
            ClassCounts right = new ClassCounts(all);
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
            if (i % 100 == 0 && i > 0) System.out.println(i);
        }
        int pi = partition(images, bestIndex, bestThreshold, low, high);
        System.out.println("pi: " + pi);
        return new Branch<Integer>(buildTree(images, maxDepth-1, low, pi), buildTree(images, maxDepth-1, pi, high), bestIndex, bestThreshold);
    }

    private static int partition(DigitImage[] images, int pixel, float threshold, int low, int high) {
        System.out.println(pixel + ":" + threshold);
        int i = (low);
        for (int j=low; j<high; j++) {
            if(images[j].getDigit()[pixel] < threshold) {
                DigitImage temp = images[i];
                images[i] = images[j];
                images[j] = temp;
                i++;
            }
        }
        DigitImage temp = images[i+1];
        images[i] = images[high-1];
        images[high-1] = temp;
        return i;
    }

    int partition(int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }

    public int classify(DigitImage image){
        return tree.predict(image);
    }
}
