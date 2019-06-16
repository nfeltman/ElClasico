package com.dugonggames.elclasico;

public class ImageSorter {
    public static void sort(DigitImage[] images, int[] labels, int pixel){
        sort(images, pixel, labels, 0, images.length-1);
    }

    private static void sort(DigitImage[] images, int pixel, int[] labels, int l, int r) {
        if (l < r) {
            int m = (l+r)/2;
            sort(images, pixel, labels, l, m);
            sort(images, pixel, labels, m+1, r);
            merge(images, pixel, labels, l, m, r);
        }
    }

    private static void merge(DigitImage[] images, int pixel, int[] labels, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        DigitImage[] L = new DigitImage[n1];
        DigitImage[] R = new DigitImage[n2];
        int[] ll = new int[n1];
        int[] rr = new int[n2];
        for (int i=0; i<n1; ++i){
            L[i] = images[l + i];
            ll[i] = labels[l+i];
        }
        for (int j=0; j<n2; ++j){
            R[j] = images[m + 1+ j];
            rr[j] = labels[m + 1+j];
        }
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j], pixel) <= 0) {
                images[k] = L[i];
                labels[k] = ll[i];
                i++;
            } else {
                images[k] = R[j];
                labels[k] = rr[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            images[k] = L[i];
            labels[k] = ll[i];
            i++;
            k++;
        }
        while (j < n2) {
            images[k] = R[j];
            labels[k] = rr[j]l
            j++;
            k++;
        }
    }

    /*private static void quickSort(DigitImage[] images, int pixel, int low, int high) {
        if (low < high) {
            int pi = partition(images, pixel, low, high);
            quickSort(images, pixel, low, pi - 1);
            quickSort(images, pixel, pi + 1, high);
        }
    }

    private static int partition(DigitImage[] images, int pixel, int low, int high) {
        DigitImage pivot = images[(int) (Math.floor(Math.random()*(high-low)) + low)];
        int i = (low-1);
        for (int j=low; j<high; j++) {
            if(images[j].compareTo(pivot, pixel) < 0) {
                i++;
                DigitImage temp = images[i];
                images[i] = images[j];
                images[j] = temp;
            }
        }
        DigitImage temp = images[i+1];
        images[i+1] = images[high];
        images[high] = temp;
        return i+1;
    }*/
}
