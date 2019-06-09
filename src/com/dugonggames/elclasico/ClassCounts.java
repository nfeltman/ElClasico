package com.dugonggames.elclasico;

public class ClassCounts {
    public int[] counts;
    private int sum;

    public ClassCounts(int size){
        counts = new int[size];
    }

    public float propMatching(int i){
        return (counts[i]+0f)/sum;
    }

    public float gimiPurity(){
        float sum = 0f;
        for (int i = 0; i < counts.length; i++){
            sum += propMatching(i) * propMatching(i);
        }
        return sum;
    }

    public int randomSelect(){
        float cumeProb = 0f;
        double random = Math.random();
        for (int i = 0; i < counts.length; i++){
            cumeProb += propMatching(i);
            if (random < cumeProb) return i;
        }
        return -1;
    }

    public int highestValue(){
        int highest = -1;
        int highestIndex = -1;
        for (int i = 0; i < counts.length; i++){
            if (counts[i] > highest){
                highest = counts[i];
                highestIndex = i;
            }
        }
        return highestIndex;
    }

    public void increment(int i){
        counts[i]++;
        sum++;
    }

    public void increment(int i, int amount){
        counts[i]+=amount;
        sum+=amount;
    }

    public void decrement(int i){
        counts[i]--;
        sum--;
    }

    public void decrement(int i, int amount){
        counts[i]-=amount;
        sum-=amount;
    }
}
