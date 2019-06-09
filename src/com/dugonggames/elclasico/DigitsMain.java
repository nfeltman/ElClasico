package com.dugonggames.elclasico;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DigitsMain {

    public static void main(String args[]) throws IOException {
        byte[] trainingimagesbytes = Files.readAllBytes(Paths.get("C:\\Users\\alexf\\Downloads\\train-images-idx3-ubyte\\train-images.idx3-ubyte"));
        byte[] traininglabelsbytes = Files.readAllBytes(Paths.get("C:\\Users\\alexf\\Downloads\\train-labels-idx1-ubyte\\train-labels.idx1-ubyte"));
        byte[] testimagesbytes = Files.readAllBytes(Paths.get("C:\\Users\\alexf\\Downloads\\t10k-images-idx3-ubyte\\t10k-images.idx3-ubyte"));
        byte[] testlabelsbytes = Files.readAllBytes(Paths.get("C:\\Users\\alexf\\Downloads\\t10k-labels-idx1-ubyte\\t10k-labels.idx1-ubyte"));

        DigitImage[] trainingimages = new DigitImage[60000];
        for (int i = 0; i < 60000; i++) trainingimages[i] = new DigitImage(trainingimagesbytes, 16+(784*i));

        int[] traininglabels = new int[60000];
        for (int i = 0; i < 60000; i++) traininglabels[i] = traininglabelsbytes[i+8];

        DistanceClassifier c = new DistanceClassifier(trainingimages, traininglabels);


        DigitImage[] testimages = new DigitImage[60000];
        for (int i = 0; i < 10000; i++) testimages[i] = new DigitImage(testimagesbytes, 16+(784*i));

        int[] testlabels = new int[60000];
        for (int i = 0; i < 10000; i++) testlabels[i] = testlabelsbytes[i+8];

        int correct = 0;
        for (int i = 0; i < 10000; i++){
            if (testlabels[i] == c.tClassify(testimages[i])) correct++;
        }
        System.out.println(correct);

        int num = 543;
        testimages[num].print();
        System.out.println(c.tClassify(testimages[num]));
        System.out.println(testlabels[num]);
    }
}
