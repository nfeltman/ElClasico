package com.dugonggames.elclasico;

public class ImageManipulation {
    public static int[] shrinkBy2(int[] image, int width, int height){
        int[] result = new int[image.length];
        for (int i = 0; i < width/2; i++){
            for (int j = 0; j < height/2; j++){
                int sum = image[(i*2+height)+j] + image[(((i*2)+1)*height)+j] + image[(i*2+height)+(j+1)]+image[(((i*2)+1)*height)+(j+1)];
                result[(i*height)+j] = sum/4;
            }
        }
        return result;
    }

    public static DigitImage blur(){
        return null;
    }

    public static int over2(int i){
        if (i%2==0) return i/2;
        else return (i/2)+1;
    }
}
