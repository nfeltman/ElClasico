package com.dugonggames.elclasico;

public class Branch<T> implements Node<T> {
    Node<T> l;
    Node<T> r;
    int xIndex;
    float xThreshold;

    public Branch(Node<T> l, Node<T> r, int xIndex, float xThreshold){
        this.l = l;
        this.r = r;
        this.xIndex = xIndex;
        this.xThreshold = xThreshold;
    }

    public T predict(DigitImage im) {
        if (im.getDigit()[xIndex] < xThreshold) return l.predict(im);
        else return r.predict(im);
    }
}
